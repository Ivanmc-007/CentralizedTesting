package com.ivan.CT.entities.dto;

import com.ivan.CT.myAnnotation.MyDateValid;
import com.ivan.CT.myAnnotation.MyTimeValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PermissionDto {

    @NotBlank(message = "Language cannot be empty!")
    private String language;

    @MyDateValid
    private String date;

    @MyTimeValid
    private String time;

    @NotNull
    private String address;

    @NotNull
    private String wayInformation;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
