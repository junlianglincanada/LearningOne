package com.example.junlianglin.learningone.utils;


import com.example.junlianglin.learningone.model.ReturnResult;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class LoginParseResult extends AbstractResponseParser {

    private LoginResult loginResult = new LoginResult();


    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        super.checkResponse(request);

        Preferences preferences = new Preferences();

        preferences.setAccessToken(headerMap.get("Token").toString(),
                Long.parseLong(headerMap.get("TokenExpiry").toString()),
                tempLastRequestDate);

    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        loginResult.result = result;
        return loginResult;
    }
}
