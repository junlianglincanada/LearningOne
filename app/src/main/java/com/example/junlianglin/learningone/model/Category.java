package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private int id;
    private String categoryName;
    private String categoryCode;

    private List<Field> formFields;

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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<Field> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<Field> formFields) {
        this.formFields = formFields;
    }
}
