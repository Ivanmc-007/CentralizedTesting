package com.ivan.CT.service;

import com.ivan.CT.entities.University;
import com.ivan.CT.repositories.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Transactional(readOnly = true)
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<University> findAllById(Iterable<Long> ids) {
        return new HashSet<>(universityRepository.findAllById(ids));
    }

    public University save(University university) {
        return universityRepository.save(university);
    }

    @Transactional(readOnly = true)
    public University findByName(String name) {
        return universityRepository.findByName(name).orElse(null);
    }

    public void remove(University university) {
        universityRepository.delete(university);
    }
}
