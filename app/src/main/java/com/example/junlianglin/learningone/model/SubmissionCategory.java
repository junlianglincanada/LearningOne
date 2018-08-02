package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.List;

public class SubmissionCategory implements Serializable {
    private int id;
    private String categoryName;
    private boolean skipped;
    private List<SubmissionField> formFields;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public List<SubmissionField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<SubmissionField> formFields) {
        this.formFields = formFields;
    }
}
