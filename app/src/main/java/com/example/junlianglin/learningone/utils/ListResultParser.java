package com.example.junlianglin.learningone.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;

public class ListResultParser extends AbstractResponseParser {


    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = builder.create();
        return  gson.fromJson(result, resultClass);
    }
}
