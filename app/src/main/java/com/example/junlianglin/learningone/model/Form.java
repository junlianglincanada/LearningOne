package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.List;

public class Form implements Serializable {
    private int id;
    private int cityId;
    private String name;
    private int startNumber;

    private List<Category> fieldCategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public List<Category> getFieldCategories() {
        return fieldCategories;
    }

    public void setFieldCategories(List<Category> fieldCategories) {
        this.fieldCategories = fieldCategories;
    }
}
