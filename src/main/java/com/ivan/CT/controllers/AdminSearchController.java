package com.ivan.CT.controllers;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.service.ChoiceService;
import com.ivan.CT.service.MyUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/search")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSearchController {

    @GetMapping("/date")
    public String getChoicesByDateCreate() throws ParseException {
        return "adminSelectChoicesByDate";
    }
}
