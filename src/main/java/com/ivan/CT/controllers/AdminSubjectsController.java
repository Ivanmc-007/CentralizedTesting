package com.ivan.CT.controllers;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import com.ivan.CT.helper.Converter;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/admin/subjects")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSubjectsController {

    private final static Logger logger = LoggerFactory.getLogger(AdminSubjectsController.class);

    private final SubjectService subjectService;

    private final UniversityService universityService;

    private final ChoiceService choiceService;

    public AdminSubjectsController(SubjectService subjectService, UniversityService universityService, ChoiceService choiceService) {
        this.subjectService = subjectService;
        this.universityService = universityService;
        this.choiceService = choiceService;
    }

    @GetMapping
    public String subjects(Model model) {
        model.addAttribute("subjects",subjectService.findAll());
        return "adminSubjects";
    }

    @PostMapping("/add")
    public String subjectsAddPost(@RequestParam String subjectName, Model model) {
        if(StringUtils.hasText(subjectName)) {
            if (Objects.isNull(subjectService.findByName(subjectName))) {
                Subject subject = new Subject();
                subject.setName(subjectName);
                // set all universities by default
                subject.setUniversities(Converter.convertListToSet(universityService.findAll()));
                subject = subjectService.save(subject);
                if (!Objects.isNull(subject.getId())) {
                    model.addAttribute("messageSuccessfulAdd", "Subject successfully added!");
                    logger.info("Subject successfully added!");
                }
            } else {
                model.addAttribute("subjectAddError", "Subject with this name exists!");
                logger.info("Subject didn't add!");
            }
        } else {
            model.addAttribute("subjectNameError", "Subject name is empty!");
        }
        model.addAttribute("subjects",subjectService.findAll());
        return "adminSubjects";
    }

    @PostMapping("/remove")
    public String removeSubjectFromDB(@RequestParam(name = "subjectId") Long subjectId) {
        Subject subject;
        if(!Objects.isNull(subject = subjectService.findById(subjectId))) {
            List<Choice> choices = choiceService.findBySubject(subject);
            if(!choices.isEmpty()) {
                for(Choice c: choices) {
                    choiceService.remove(c);
                }
            }
            subjectService.removeById(subjectId);
            logger.info("id: "+subject.getId()+","+subject.getName() + " was removed!");
        }
        return "redirect:/admin/subjects";
    }

    @PostMapping("/rename")
    public String renameSubjectFromDBPost(@RequestParam(name = "subjectId") Subject subject,
                                          @RequestParam(name = "newNameSubject") String newNameSubject) {
        subject.setName(newNameSubject);
        subjectService.save(subject);
        logger.info("Updated name of subject with id: "+subject.getId());
        return "redirect:/admin/subjects";
    }

    @GetMapping("/rename")
    public String renameSubjectFromDB(@RequestParam(name = "subjectId") Subject subject,
                                      Model model) {
        model.addAttribute("subject",subject);
        return "renameSubjectForm";
    }

    @GetMapping("/setUniversities")
    public String setUniversities(@RequestParam(name = "subjectId") Subject subject,
                                  Model model) {
        model.addAttribute("subject", subject);
        List<University> universities = universityService.findAll();
        model.addAttribute("universities", universities);
        Set<University> universitiesOfSubject = subject.getUniversities();
        model.addAttribute("universitiesOfSubject", universitiesOfSubject);
        return "setUniversities";
    }

    @PostMapping("/setUniversities")
    public String setUniversitiesPost(@RequestParam(required = false) Long [] universityId,
                                      @RequestParam(name = "subjectId") Subject subject) {
        if(universityId == null)
            universityId = new Long[0];
        Set<University> universities = universityService.findAllById(Arrays.asList(universityId));
        subject.setUniversities(universities);
        subjectService.save(subject);
        return "redirect:/admin/subjects";
    }

}
