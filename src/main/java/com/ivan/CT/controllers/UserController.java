package com.ivan.CT.controllers;

import com.ivan.CT.entities.MyUser;
import com.ivan.CT.entities.Passport;
import com.ivan.CT.helper.Converter;
import com.ivan.CT.myAnnotation.CurrentUser;
import com.ivan.CT.service.MyMailSender;
import com.ivan.CT.service.MyUserService;
import com.ivan.CT.service.PassportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/myUser")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final MyUserService myUserService;

    private final PassportService passportService;

    private final MyMailSender myMailSender;

    public UserController(MyUserService myUserService, PassportService passportService, MyMailSender myMailSender) {
        this.myUserService = myUserService;
        this.passportService = passportService;
        this.myMailSender = myMailSender;
    }

    @GetMapping
    public String myRoom() {
        return "myRoom";
    }

    @PostMapping("/setDocument")
    public String setIdentityDocument(@CurrentUser MyUser myUser,
                                      @Valid Passport passport,
                                      BindingResult bindingResult,
                                      Model model) {
        // if identityDocument isInvalid
        if(bindingResult.hasErrors()) {
            Map<String,String> errorMap = Converter.convertBindingResultToMap(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("docIsInvalid",true);
            model.addAttribute("currentPassport", myUserService.findById(myUser.getId()).getPassport());
            return "myRoomSetIdentityDocument";
        }

        MyUser currentUserDB;
        if((currentUserDB = myUserService.findById(myUser.getId())) != null) {
            if(currentUserDB.hasDocument()) {
                Passport currPassport = currentUserDB.getPassport();
                currPassport.setFirstName(passport.getFirstName());
                currPassport.setLastName(passport.getLastName());
                currPassport.setNumber(passport.getNumber());
                currPassport.setSeries(passport.getSeries());
                passportService.save(currPassport);
            } else {
                passport = passportService.save(passport);
                currentUserDB.setPassport(passport);
                myUserService.save(currentUserDB);
            }
            logger.info("LOGGER: user set IdentDoc successfully!");
        }
        return "redirect:/myUser/identityDocument";
    }

    @GetMapping("/identityDocument")
    public String myRoomSetIdentityDocument(@CurrentUser MyUser myUser,
                                            Model model) {
        MyUser currentUserDB;
        if((currentUserDB = myUserService.findById(myUser.getId())) != null && currentUserDB.hasDocument()) {
            Passport passport = currentUserDB.getPassport();
            model.addAttribute("currentPassport", passport);
            if(!model.containsAttribute("passport"))
                model.addAttribute("passport", passport);
        }
        return "myRoomSetIdentityDocument";
    }

    @GetMapping("/profile")
    public String updateProfile(@CurrentUser MyUser myUser,
                                 Model model) {
        MyUser currentUserDB;
        if((currentUserDB = myUserService.findById(myUser.getId())) != null) {
            model.addAttribute("currEmail", currentUserDB.getEmail());
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfilePost(@CurrentUser MyUser myUser,
                                    @RequestParam String password,
                                    @RequestParam String newPassword,
                                    @RequestParam String newEmail,
                                    Map<String, Object> model) {
        MyUser myUserDB;
        if(!Objects.isNull(myUserDB = myUserService.findById(myUser.getId()))) {
            if(myUserDB.getPassword().equals(password)) {
                if(!StringUtils.hasText(newPassword)) {  // заглушка ... проверка на корректность todo: переписать и вынести в отдельный метод
                    model.put("newPasswordError", "New password cannot be empty or contain only space symbols!");
                }
                if(StringUtils.isEmpty(newEmail))
                    model.put("emailError", "Email cannot be empty!");
                if(myUserDB.getPassword().equals(newPassword)) {
                    model.put("newPasswordError", "New password cannot be equals current password!");
                }

                if(model.isEmpty()) {
                    myUserDB.setPassword(newPassword);
                    myUserDB.setEmail(newEmail);
                    myUserService.save(myUserDB);
                    model.put("currEmail", myUserDB.getEmail());
                    model.put("messageUpdate","Profile successfully updated!");
                    myMailSender.sendMessage(myUserDB.getEmail(),"Profile change", "Profile updated!");
                    logger.info("User with "+ myUser.getId() +" updated");
                    return "profile";
                }
            } else {
                model.put("passwordError", "Wrong password!");
            }
        }
        if(!Objects.isNull(myUserDB))
            model.put("currEmail", myUserDB.getEmail());
        return "profile";
    }

}
