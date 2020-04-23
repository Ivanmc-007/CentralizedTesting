package com.ivan.CT.entities;

import com.ivan.CT.myAnnotation.MyDateValid;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Permission extends AbstractEntity {
    @NotBlank
    private String language;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStartTest;

    private String address;

    private String wayInformation;

    @OneToOne
    private Choice choice;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getDateStartTest() {
        return dateStartTest;
    }

    public void setDateStartTest(Date dateStartTest) {
        this.dateStartTest = dateStartTest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWayInformation() {
        return wayInformation;
    }

    public void setWayInformation(String wayInformation) {
        this.wayInformation = wayInformation;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
