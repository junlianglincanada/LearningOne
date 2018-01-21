package com.example.junlianglin.framework.utils;

import java.util.Locale;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public class SystemUtil {
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getSystemInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n System Information:\n");
        stringBuffer.append("\n Maker:").append(getDeviceBrand()).append("\n");
        stringBuffer.append("\n Model:").append(getSystemModel()).append("\n");
        stringBuffer.append("\n Language:").append(getSystemLanguage()).append("\n");
        stringBuffer.append("\n Android Version:").append(getSystemVersion()).append("\n");
        return  stringBuffer.toString();


    }
}
