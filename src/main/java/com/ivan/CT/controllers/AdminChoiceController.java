package com.ivan.CT.controllers;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.Permission;
import com.ivan.CT.entities.dto.PermissionDto;
import com.ivan.CT.helper.Converter;
import com.ivan.CT.helper.DtoConverter;
import com.ivan.CT.helper.MyDateParser;
import com.ivan.CT.service.ChoiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/admin/choices")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminChoiceController {

    private final ChoiceService choiceService;

    private final DtoConverter dtoConverter;

    public AdminChoiceController(ChoiceService choiceService, DtoConverter dtoConverter) {
        this.choiceService = choiceService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public String getAll(@RequestParam(name = "calendarStart", required = false) String dateStart,
                         @RequestParam(name = "calendarEnd", required = false) String dateEnd,
                         Model model) throws ParseException {
        if (StringUtils.isEmpty(dateStart) || StringUtils.isEmpty(dateEnd)) {
            model.addAttribute("choices", new ArrayList<Choice>());
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(dateStart);
            Date end = format.parse(dateEnd);
            model.addAttribute("calendarStart", dateStart);
            model.addAttribute("calendarEnd", dateEnd);
            model.addAttribute("choices", choiceService.findByDateCreationBetween(start, end));
        }
        return "adminChoices";
    }

    @GetMapping("/{choiceId}")
    public String getChoice(@PathVariable(name = "choiceId") Choice choice, Model model) {
        setFieldsPage(choice, model);
        return "adminSetPermission";
    }

    @PostMapping("/{choiceId}")
    public String setPermission(@PathVariable(name = "choiceId") Choice choice,
                                @Valid PermissionDto permissionDto,
                                BindingResult bindingResult,
                                Model model) throws ParseException {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(Converter.convertBindingResultToMap(bindingResult));
            setFieldsPage(choice,model);
            return "adminSetPermission";
        } else {
            Permission permission = dtoConverter.convertToEntity(permissionDto);
            if (choice.getPermission() == null) {
                choice.setPermission(permission);
            } else {
                choice.getPermission().setDateStartTest(permission.getDateStartTest());
                choice.getPermission().setAddress(permission.getAddress());
                choice.getPermission().setLanguage(permission.getLanguage());
                choice.getPermission().setWayInformation(permission.getWayInformation());
            }
            choice.setAccept(true);
            choiceService.save(choice);
        }
        return String.format("redirect:/admin/choices/%s", choice.getId());
    }

    private void setFieldsPage(Choice choice, Model model) {
        model.addAttribute("choice", choice);
        if (choice.isAccept()) {
            Permission permission = choice.getPermission();
            model.addAttribute("permission", permission);
            Date date = permission.getDateStartTest();
            model.addAttribute("dateStartTest", MyDateParser.getJustDate(date));
            model.addAttribute("timeStartTest", MyDateParser.getJustTime(date));
        }
    }
}
