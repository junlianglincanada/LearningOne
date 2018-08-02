package com.example.junlianglin.learningone.model;

import com.example.junlianglin.learningone.utils.ListResultParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;
@HttpResponse(parser = ListResultParser.class)
public class Data extends ReturnResult {
    private List<DictionaryData> rentalType;

    private List<DictionaryData> formSubmissionStatus;

    private List<DictionaryData> formFieldPassFail;

    private List<DictionaryData> hazardRating;

    private List<DictionaryData> formType;

    private List<DictionaryData> formFieldCorrectiveAction;

    private List<DictionaryData> formFieldChargeTo;

    private List<Building> buildingList;

    private List<Form> formList;

    private List<Task> taskList;

    public List<DictionaryData> getRentalType() {
        return rentalType;
    }

    public void setRentalType(List<DictionaryData> rentalType) {
        this.rentalType = rentalType;
    }

    public List<DictionaryData> getFormSubmissionStatus() {
        return formSubmissionStatus;
    }

    public void setFormSubmissionStatus(List<DictionaryData> formSubmissionStatus) {
        this.formSubmissionStatus = formSubmissionStatus;
    }

    public List<DictionaryData> getFormFieldPassFail() {
        return formFieldPassFail;
    }

    public void setFormFieldPassFail(List<DictionaryData> formFieldPassFail) {
        this.formFieldPassFail = formFieldPassFail;
    }

    public List<DictionaryData> getHazardRating() {
        return hazardRating;
    }

    public void setHazardRating(List<DictionaryData> hazardRating) {
        this.hazardRating = hazardRating;
    }

    public List<DictionaryData> getFormType() {
        return formType;
    }

    public void setFormType(List<DictionaryData> formType) {
        this.formType = formType;
    }

    public List<DictionaryData> getFormFieldCorrectiveAction() {
        return formFieldCorrectiveAction;
    }

    public void setFormFieldCorrectiveAction(List<DictionaryData> formFieldCorrectiveAction) {
        this.formFieldCorrectiveAction = formFieldCorrectiveAction;
    }

    public List<DictionaryData> getFormFieldChargeTo() {
        return formFieldChargeTo;
    }

    public void setFormFieldChargeTo(List<DictionaryData> formFieldChargeTo) {
        this.formFieldChargeTo = formFieldChargeTo;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Form> getFormList() {
        return formList;
    }

    public void setFormList(List<Form> formList) {
        this.formList = formList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
