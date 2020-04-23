package com.ivan.CT.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class University extends AbstractEntity {
    private String name;

    @ManyToMany/*(mappedBy = "universities")*/
    @JoinTable(
            name = "subject_university",
            joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
