package com.ivan.CT.controllers;

import com.ivan.CT.entities.MyUser;
import com.ivan.CT.helper.Converter;
import com.ivan.CT.service.MyUserService;
import com.ivan.CT.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;

    private final MyUserService myUserService;

    public RegistrationController(RegistrationService registrationService, MyUserService myUserService) {
        this.registrationService = registrationService;
        this.myUserService = myUserService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registrationPost(@RequestParam(name = "password2")String password2,
                                   @Valid MyUser myUser,
                                   BindingResult bindingResult,
                                   Model model) {
        if(myUser.getPassword() != null && !myUser.getPassword().equals(password2)) {
            model.addAttribute("password2Error","Passwords are different!");
            return "registration";
        }

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = Converter.convertBindingResultToMap(bindingResult);
            model.mergeAttributes(errorMap);
            return "registration";
        }

        if(registrationService.addUserInDB(myUser)) {
            model.addAttribute("messageSuccessfullyRegistration","You have successfully registered! Check your email to activate your account!");
            logger.info("User has registered");
        } else {
            model.addAttribute("loginError", "User exists!");
        }
        return "registration";
    }

    @GetMapping("/activate/{activationCode}")
    public String activation(@PathVariable String activationCode, Model model) {
        MyUser myUser = myUserService.findByActivationCode(activationCode);
        if(myUser != null) {
            myUser.setActive(true);
            myUser.setActivationCode(null);
            myUserService.save(myUser);
            model.addAttribute("messageActivation","Activation accepted!");
        }
        return "start";
    }
}
