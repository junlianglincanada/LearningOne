package com.example.junlianglin.learningone.utils;

import com.example.junlianglin.learningone.model.ReturnResult;

import org.xutils.http.annotation.HttpResponse;

@HttpResponse(parser =LoginParseResult.class)
public class LoginResult extends ReturnResult {

    public String result;



}
