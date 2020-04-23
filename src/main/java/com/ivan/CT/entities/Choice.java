package com.ivan.CT.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Choice extends AbstractEntity {

    @NotNull
    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Subject subject;

    @NotNull
    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private University university;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @NotNull
    @ManyToOne
    private MyUser myUser;

    private boolean accept;

    @OneToOne(cascade = CascadeType.ALL)
    private Permission permission;

    public Choice() {
    }

    public Choice(Subject subject, University university, Date dateCreation, MyUser myUser) {
        this.subject = subject;
        this.university = university;
        this.dateCreation = dateCreation;
        this.myUser = myUser;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
