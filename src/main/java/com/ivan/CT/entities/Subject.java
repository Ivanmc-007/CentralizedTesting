package com.ivan.CT.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Subject extends AbstractEntity {
    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(
            name = "subject_university",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    private Set<University> universities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return this.getId().equals(subject.getId())
                && name.equals(subject.name);
                //&& universities.equals(subject.universities);
    }

}
