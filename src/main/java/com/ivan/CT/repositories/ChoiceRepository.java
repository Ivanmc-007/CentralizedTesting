package com.ivan.CT.repositories;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.Subject;
import com.ivan.CT.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice,Long> {
    List<Choice> findBySubject(Subject subject);
    List<Choice> findByUniversity(University university);
    List<Choice> findByDateCreationGreaterThanEqualAndDateCreationLessThanEqual(Date endDate, Date startDate);
}
