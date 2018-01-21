package com.example.junlianglin.learningone.model;

import java.io.Serializable;

/**
 * Created by JunliangLin on 1/8/2018.
 */

public class News implements Serializable {
    private String title;
    private String date;
    private int comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
