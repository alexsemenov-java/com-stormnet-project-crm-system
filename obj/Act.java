package com.stormnet.crm.system.obj;

import java.time.LocalDate;

public class Act extends Id {
    private LocalDate date;
    private String time;
    private Integer clientId;
    private Integer managerId;
    private Boolean isFinished;
    private String clientFirstName;
    private String clientLastName;
    private String managerFirstName;
    private String managerLastName;
    private String office;
    private String clientComment;
    private String managerComment;
    private String rating;
    private String phoneNumber;



    public Act() {
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getManagerId() {
        return this.managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Boolean getFinished() {
        return this.isFinished;
    }

    public void setFinished(Boolean finished) {
        this.isFinished = finished;
    }

    public String getClientFirstName() {
        return this.clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return this.clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getManagerFirstName() {
        return this.managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return this.managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getClientComment() {
        return this.clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public String getManagerComment() {
        return this.managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

