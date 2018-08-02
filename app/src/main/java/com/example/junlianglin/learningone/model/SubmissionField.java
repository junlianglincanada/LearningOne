package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SubmissionField implements Serializable {
    private int id;
    private int formFieldId;
    private String formFieldName;
    private String status;
    private String workingRequested;
    private String workingStatus;
    private Date dateScheduled;
    private String contractor;
    private String wo;
    private String personInCharge;
    private String costChargedToName;
    private boolean cleaning;
    private String requiresCleaning;
    private boolean painting;
    private String requiresPainting;
    private int formFieldDataType;
    private int passFail;
    private List<Comment> comments;
    private List<Photo> photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(int formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkingRequested() {
        return workingRequested;
    }

    public void setWorkingRequested(String workingRequested) {
        this.workingRequested = workingRequested;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getCostChargedToName() {
        return costChargedToName;
    }

    public void setCostChargedToName(String costChargedToName) {
        this.costChargedToName = costChargedToName;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public String getRequiresCleaning() {
        return requiresCleaning;
    }

    public void setRequiresCleaning(String requiresCleaning) {
        this.requiresCleaning = requiresCleaning;
    }

    public boolean isPainting() {
        return painting;
    }

    public void setPainting(boolean painting) {
        this.painting = painting;
    }

    public String getRequiresPainting() {
        return requiresPainting;
    }

    public void setRequiresPainting(String requiresPainting) {
        this.requiresPainting = requiresPainting;
    }

    public int getFormFieldDataType() {
        return formFieldDataType;
    }

    public void setFormFieldDataType(int formFieldDataType) {
        this.formFieldDataType = formFieldDataType;
    }

    public int getPassFail() {
        return passFail;
    }

    public void setPassFail(int passFail) {
        this.passFail = passFail;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
