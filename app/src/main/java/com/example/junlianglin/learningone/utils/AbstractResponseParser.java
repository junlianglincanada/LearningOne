package com.example.junlianglin.learningone.utils;

import android.content.Context;
import android.preference.PreferenceActivity;

import com.example.junlianglin.framework.application.MyApplication;

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

public abstract class AbstractResponseParser implements ResponseParser {
    protected Context context;
    protected Map<String,String> headerMap = new HashMap<String,String>();
    protected String serverDateFormatPattern = "EE, dd MMM yyyy HH:mm:ss 'GMT'";
    protected long tempLastRequestDate;

    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        context = MyApplication.getMyApplication().getApplicationContext();
        if (!request.getResponseHeaders().isEmpty()){
            Iterator<String> hearders = request.getResponseHeaders().keySet().iterator();
            while (hearders.hasNext()) {
                String key = hearders.next();
                headerMap.put(key,request.getResponseHeaders().get(key).toString().replace("[","")
                        .replace("]",""));
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat(serverDateFormatPattern, Locale.ENGLISH);
        Date parsedDate = null;
        try {
            parsedDate = sdf.parse(headerMap.get("Date").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(parsedDate);
        tempLastRequestDate = c.getTimeInMillis();

        Preferences preferences = new Preferences();
        System.out.println("update LastRequestDate from " + preferences.getLastRequestDataSeconds() + " to " + tempLastRequestDate);
        preferences.updateLastRequestDataSeconds(tempLastRequestDate);



    }

    @Override
    public abstract Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable ;
}
