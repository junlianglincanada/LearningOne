package com.example.junlianglin.learningone.model;

import java.io.Serializable;

public class Field implements Serializable {
    private int id;
    private String name;
    private String formFieldDataType;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormFieldDataType() {
        return formFieldDataType;
    }

    public void setFormFieldDataType(String formFieldDataType) {
        this.formFieldDataType = formFieldDataType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
