package com.ivan.CT.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Passport extends AbstractEntity {

    @NotBlank
    private String series;

    @NotBlank
    private String number;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
