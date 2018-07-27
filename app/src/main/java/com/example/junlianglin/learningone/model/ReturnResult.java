package com.example.junlianglin.learningone.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ReturnResult  {
    private Map<String,String> headerMap = new HashMap<String,String>();

    protected Pagination pagination;



    public Pagination getPagination() {
        return pagination;
    }

    public Map getHeaderMap(){return headerMap;}
}
