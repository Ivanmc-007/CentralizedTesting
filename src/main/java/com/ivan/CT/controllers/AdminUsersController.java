package com.ivan.CT.controllers;

import com.ivan.CT.entities.MyUser;
import com.ivan.CT.entities.Role;
import com.ivan.CT.myAnnotation.CurrentUser;
import com.ivan.CT.service.MyUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUsersController {

    private final MyUserService myUserService;

    public AdminUsersController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", myUserService.findAll());
        return "adminUsers";
    }

    @PostMapping("{myUserId}")
    public String saveUserPost(@PathVariable(name = "myUserId") MyUser myUser,
                               @RequestParam(name = "myUserLogin") String myUserLogin,
                               @RequestParam(name = "checkedRoles", required = false) String [] checkedRoles,
                               Model model
                               ) {
        if(Objects.isNull(checkedRoles))
            checkedRoles = new String[0];

        if(!StringUtils.isEmpty(myUserLogin))
            myUser.setLogin(myUserLogin);
        else {
            model.addAttribute("myUserLoginError","Login cannot be empty!");
            model.addAttribute("myUserEdit", myUser);
            model.addAttribute("roles", Role.values());
            return "adminMyUserEdit";
        }
        myUser.getRoles().clear();
        Arrays.stream(checkedRoles).forEach(strRole->myUser.getRoles().add(Role.valueOf(strRole)));
        myUserService.save(myUser);
        return "redirect:/admin/users";
    }

    @GetMapping("{myUserId}")
    public String editor(@PathVariable(name = "myUserId") MyUser myUser, Model model) {
        model.addAttribute("myUserEdit", myUser);
        model.addAttribute("roles", Role.values());
        return "adminMyUserEdit";
    }

    @GetMapping("/show/{myUserId}")
    public String show(@PathVariable(name = "myUserId") MyUser myUser, Model model) {
        model.addAttribute("currMyUser", myUser);
        return "myUserShowAll";
    }

    @GetMapping("/removeAccept/{myUserId}")
    public String removeAccept(@PathVariable(name = "myUserId") Long myUserId, Model model) {
        model.addAttribute("removeMyUserId", myUserId);
        return "adminAcceptRemove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam(name = "removeMyUserId") Long myUserId, @CurrentUser MyUser currUser, Model model) {

        if(!currUser.getId().equals(myUserId)) {
            myUserService.removeById(myUserId);
            return "redirect:/admin/users";
        }

        model.addAttribute("removeError", "You cannot remove yourself!");
        model.addAttribute("users", myUserService.findAll());
        return "adminUsers";
    }

}
