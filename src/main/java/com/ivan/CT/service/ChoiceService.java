package com.ivan.CT.service;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import com.ivan.CT.repositories.ChoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public Choice save(Choice choice) {
        return choiceRepository.save(choice);
    }

    public void remove(Choice choice) {
        choiceRepository.delete(choice);
    }

    @Transactional(readOnly = true)
    public List<Choice> findBySubject(Subject subject) {
        return choiceRepository.findBySubject(subject);
    }

    @Transactional(readOnly = true)
    public List<Choice> findByUniversity(University university) {
        return choiceRepository.findByUniversity(university);
    }

    public void removeAll(Iterable<Choice> choices) {
        choiceRepository.deleteAll(choices);
    }

    @Transactional(readOnly = true)
    public List<Choice> findByDateCreationBetween(Date start, Date end) {
        // offset on 24 hours
//        long delta = 24*60*60*1000;
//        Date newEnd = new Date(end.getTime()+delta);
//        Date newStart = new Date(start.getTime()+delta);
        return choiceRepository.findByDateCreationGreaterThanEqualAndDateCreationLessThanEqual(start, end);
    }

    @Transactional(readOnly = true)
    public List<Choice> findAll() {
        return choiceRepository.findAll();
    }

}
