package com.ivan.CT.service;

import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import com.ivan.CT.repositories.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Subject findByName(String name) {
        return subjectRepository.findByName(name).orElse(null);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void removeById(Long id) {
        if(!Objects.isNull(id))
            subjectRepository.deleteById(id);
    }

    public void remove(Subject subject) {
        subjectRepository.delete(subject);
    }

    public List<Subject> saveAll(Iterable<Subject> subjects) {
        return subjectRepository.saveAll(subjects);
    }

    @Transactional(readOnly = true)
    public Set<Subject> findAllById(Iterable<Long> ids) {
        return new HashSet<>(subjectRepository.findAllById(ids));
    }
}
