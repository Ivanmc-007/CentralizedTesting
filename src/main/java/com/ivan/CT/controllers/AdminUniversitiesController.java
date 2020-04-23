package com.ivan.CT.controllers;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import com.ivan.CT.service.ChoiceService;
import com.ivan.CT.service.SubjectService;
import com.ivan.CT.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/admin/universities")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUniversitiesController {
    private final static Logger logger = LoggerFactory.getLogger(AdminUniversitiesController.class);

    private final UniversityService universityService;

    private final SubjectService subjectService;

    private final ChoiceService choiceService;

    public AdminUniversitiesController(UniversityService universityService, SubjectService subjectService, ChoiceService choiceService) {
        this.universityService = universityService;
        this.subjectService = subjectService;
        this.choiceService = choiceService;
    }

    @GetMapping
    public String universities(Model model) {
        model.addAttribute("universities", universityService.findAll());
        return "adminUniversities";
    }

    @GetMapping("/rename")
    public String renameUniversity(@RequestParam(name = "universityId") University university,
                         Model model) {
        model.addAttribute("university",university);
        return "renameUniversityForm";
    }

    @PostMapping("/rename")
    public String renameUniversityPost(@RequestParam(name = "universityId") University university,
                                          @RequestParam(name = "newNameUniversity") String newNameUniversity) {
        university.setName(newNameUniversity);
        universityService.save(university);
        logger.info("Updated name of university with id: "+university.getId());
        return "redirect:/admin/universities";
    }

    @PostMapping("/add")
    public String subjectsAddPost(@RequestParam String universityName, Model model) {
        if(StringUtils.hasText(universityName)) {
            if (Objects.isNull(universityService.findByName(universityName))) {
                University university = new University();
                university.setName(universityName);
                university.setSubjects(new HashSet<>(subjectService.findAll()));
                universityService.save(university);
            } else {
                model.addAttribute("universityNameError", String.format("University with this name '%s' exists!",universityName));
                logger.info("Subject didn't add!");
            }
        } else {
            model.addAttribute("universityNameError", "University name is empty!");
        }
        model.addAttribute("universities",universityService.findAll());
        return "adminUniversities";
    }

    @PostMapping("/remove")
    public String removeUniversityFromDB(@RequestParam(name = "universityId") University university) {
        List<Choice> choices = choiceService.findByUniversity(university);
        if(!choices.isEmpty())
            choiceService.removeAll(choices);

        universityService.remove(university);
        logger.info("id: "+university.getId()+","+university.getName() + " was removed!");
        return "redirect:/admin/universities";
    }

    @GetMapping("/setSubjects")
    public String setUniversities(@RequestParam(name = "universityId") University university,
                                  Model model) {
        model.addAttribute("university", university);
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        Set<Subject> subjectsOfUniversity = university.getSubjects();
        model.addAttribute("subjectsOfUniversity", subjectsOfUniversity);
        return "setSubjects";
    }

    @PostMapping("/setSubjects")
    public String setUniversitiesPost(@RequestParam(required = false) Long [] subjectId,
                                      @RequestParam(name = "universityId") University university) {
        if(subjectId == null)
            subjectId = new Long[0];
        Set<Subject> subjects = subjectService.findAllById(Arrays.asList(subjectId));
        university.setSubjects(subjects);
        universityService.save(university);
        return "redirect:/admin/universities";
    }

}
