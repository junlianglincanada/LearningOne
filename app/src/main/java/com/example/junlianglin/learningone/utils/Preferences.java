package com.example.junlianglin.learningone.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.junlianglin.framework.application.MyApplication;

import java.util.Calendar;
import java.util.TimeZone;

public class Preferences {
    private static final String SHAREDPREFERENCE_NAME = "MyCareTaker";
    private Context context;

    public Preferences(){
        context = MyApplication.getMyApplication().getApplicationContext();
    }

    public  void setAccessToken(String token,long tokenExpirySeconds,long lastRequestDataSeconds) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESSTOKEN", token);
        editor.putLong("TOKENEXPIRYSECONDS",tokenExpirySeconds);
        editor.putLong("LASTREQUESTDATASECONDS",lastRequestDataSeconds);
        editor.apply();
    }

    public void updateLastRequestDataSeconds(long lastRequestDataSeconds){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("LASTREQUESTDATASECONDS",lastRequestDataSeconds);
        editor.apply();
    }

    public  String getAccessToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("ACCESSTOKEN", null);
    }

    public  long getTokenExpirySeconds(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong("TOKENEXPIRYSECONDS",0);
    }

    public  long getLastRequestDataSeconds(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong("LASTREQUESTDATASECONDS",0);
    }

    public  boolean isTokenValid(){
        if (getAccessToken()==null){
            return false;
        }
        else{

            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT"));
            long currentDateSeconds =c.getTimeInMillis();
            System.out.println("token=" + getAccessToken());
            System.out.println("tokenExpirySeconds=" + getTokenExpirySeconds());
            System.out.println("LastRequestDataSeconds=" + getLastRequestDataSeconds());
            System.out.println("currentDateSeconds=" + currentDateSeconds);
            System.out.println();

            if ((getTokenExpirySeconds()+getLastRequestDataSeconds())>=currentDateSeconds){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
