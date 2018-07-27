package com.example.junlianglin.learningone.model;

import com.example.junlianglin.learningone.utils.ListResultParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;
@HttpResponse(parser = ListResultParser.class)
public class TaskList extends ReturnResult{
    protected List<Task> data;

    public List<Task> getData() {
        return data;
    }

    /*public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }*/
}
