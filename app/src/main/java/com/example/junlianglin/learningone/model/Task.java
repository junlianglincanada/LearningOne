package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {
    public int id;
    public int reportNumber;
    public String formName;
    public String status;
    public String rentalType;
    public String buildingAddress;
    public int unitNumber;
    public Date inspectedDate;
    public Submission submissionDetails;

    public Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public Submission getSubmissionDetails() {
        return submissionDetails;
    }

    public void setSubmissionDetails(Submission submissionDetails) {
        this.submissionDetails = submissionDetails;
    }
}
