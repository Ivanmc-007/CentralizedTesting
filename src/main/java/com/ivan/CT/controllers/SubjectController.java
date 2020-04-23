package com.ivan.CT.controllers;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.MyUser;
import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import com.ivan.CT.myAnnotation.CurrentUser;
import com.ivan.CT.service.ChoiceService;
import com.ivan.CT.service.MyUserService;
import com.ivan.CT.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/myUser/subject")
public class SubjectController {

    private final SubjectService subjectService;

    private final MyUserService myUserService;

    private final ChoiceService choiceService;

    public SubjectController(SubjectService subjectService, MyUserService myUserService, ChoiceService choiceService) {
        this.subjectService = subjectService;
        this.myUserService = myUserService;
        this.choiceService = choiceService;
    }

    @PostMapping
    public String addSubjectPost(@CurrentUser MyUser myUser,
                                 Model model,
                                 @RequestParam(name = "subjectIdSelected") Subject subject,
                                 @RequestParam(name = "universityIdSelected", required = false) University university
    ) {

            MyUser currentUser;
            if ((currentUser = myUserService.findById(myUser.getId())) != null && currentUser.hasDocument()) {
                Set<Choice> currChoices = currentUser.getChoices();

                for (Choice c : currChoices) {
                    if (c.getSubject().equals(subject)) {
                        // todo: may be вынести в отдельный метод
                        model.addAttribute("messageWarningSubject", "You cannot add an item because they exist!");
                        model.addAttribute("subjectsDB", subjectService.findAll());
                        model.addAttribute("choices", currentUser.getChoices());
                        return "myRoomSubjects";
                    }
                }

                if(Objects.nonNull(university)) {
                    Date dateCreation = Calendar.getInstance().getTime();
                    Choice choice = new Choice(subject,university,dateCreation,currentUser);
                    currChoices.add(choice);
                    currentUser.setChoices(currChoices);
                    myUserService.save(currentUser);
                } else {
                    // todo: may be вынести в отдельный метод
                    model.addAttribute("messageWarningSubject", "You cannot add a subject without university!");
                    model.addAttribute("subjectsDB", subjectService.findAll());
                    model.addAttribute("choices", currentUser.getChoices());
                    return "myRoomSubjects";
                }
            } else {
                model.addAttribute("messageWarningSubject", "You cannot add subjects without Identity Document!");
            }
            return "redirect:/myUser/subject";
    }

    @GetMapping
    public String addSubject(@CurrentUser MyUser myUser,
                             Model model,
                             @RequestParam(name = "subjectId", required = false) Subject subject) {
        MyUser currentUser;
        if((currentUser = myUserService.findById(myUser.getId())) != null && currentUser.hasDocument()) {
            model.addAttribute("subjectsDB", subjectService.findAll());
            model.addAttribute("choices", currentUser.getChoices());
            if(!Objects.isNull(subject)) {
                model.addAttribute("universitiesOfSubject", subject.getUniversities());
                model.addAttribute("subjectIdSet",subject.getId());
            }
        } else {
            model.addAttribute("messageWarningSubject", "You cannot add subjects without Identity Document!");
        }
        model.addAttribute("subjectsDB", subjectService.findAll());
        return "myRoomSubjects";
    }

    @PostMapping("/removeSubject")
    public String removeSubjectInMyUserPost(@CurrentUser MyUser myUser,
                                            @RequestParam(name = "choiceId") Choice choice) {
        MyUser currentUser;

        if((currentUser = myUserService.findById(myUser.getId())) != null) {
            Set<Choice> choices = currentUser.getChoices();
            if(choices.remove(choice)) {
                currentUser.setChoices(choices);
                choiceService.remove(choice);
                myUserService.save(currentUser);
            }
        }
        return "redirect:/myUser/subject";
    }

    @GetMapping("/removeSubject")
    public String removeSubjectInMyUser(@RequestParam(name = "choiceId") Choice choice, Model model) {
        model.addAttribute("choice", choice);
        return "removeSubjectByUserFrom";
    }

}
