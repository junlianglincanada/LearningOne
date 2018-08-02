package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Submission implements Serializable {
    private int id;
    private int reportNumber;
    private String status;
    private String rentalType;
    private String buildingAddress;
    private String buildingUnitName;
    private String tenantName;
    private Date moveInDate;
    private Date moveOutDate;
    private String tenantEmail;
    private String tenantPhoneNumber;
    private boolean tenantOnSite;
    private Date inspectDate;
    private String createUser;
    private Date confirmedDate;
    private String confirmedUser;
    private String firstWitnessEmail;
    private Date emailNotificationDate;
    private String forwardingAddress;
    private List<SubmissionCategory> fieldCategories;


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

    public String getBuildingUnitName() {
        return buildingUnitName;
    }

    public void setBuildingUnitName(String buildingUnitName) {
        this.buildingUnitName = buildingUnitName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Date getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(Date moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public String getTenantPhoneNumber() {
        return tenantPhoneNumber;
    }

    public void setTenantPhoneNumber(String tenantPhoneNumber) {
        this.tenantPhoneNumber = tenantPhoneNumber;
    }

    public boolean isTenantOnSite() {
        return tenantOnSite;
    }

    public void setTenantOnSite(boolean tenantOnSite) {
        this.tenantOnSite = tenantOnSite;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getFirstWitnessEmail() {
        return firstWitnessEmail;
    }

    public void setFirstWitnessEmail(String firstWitnessEmail) {
        this.firstWitnessEmail = firstWitnessEmail;
    }

    public Date getEmailNotificationDate() {
        return emailNotificationDate;
    }

    public void setEmailNotificationDate(Date emailNotificationDate) {
        this.emailNotificationDate = emailNotificationDate;
    }

    public String getForwardingAddress() {
        return forwardingAddress;
    }

    public void setForwardingAddress(String forwardingAddress) {
        this.forwardingAddress = forwardingAddress;
    }

    public List<SubmissionCategory> getFieldCategories() {
        return fieldCategories;
    }

    public void setFieldCategories(List<SubmissionCategory> fieldCategories) {
        this.fieldCategories = fieldCategories;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public String getConfirmedUser() {
        return confirmedUser;
    }

    public void setConfirmedUser(String confirmedUser) {
        this.confirmedUser = confirmedUser;
    }
}
