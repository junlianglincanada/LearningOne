package com.example.junlianglin.framework.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.junlianglin.framework.exception.BaseExceptionHandler;
import com.example.junlianglin.framework.exception.LocalFileHandler;

import static java.lang.Thread.getDefaultUncaughtExceptionHandler;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public abstract class BaseApplication extends Application {
    public static Context applicationContext;
    public SharedPreferences sharedPreferences;




    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        if (getDefaultUncaughtExceptionHandler()==null){
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));

        }
        else {
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }

        sharedPreferences = getSharedPreferences("LearningOne",MODE_PRIVATE);

    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
