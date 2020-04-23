package com.ivan.CT.repositories;

import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University,Long> {
    Optional<University> findByName(String name);
}
