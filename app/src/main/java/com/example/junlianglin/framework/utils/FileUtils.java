package com.example.junlianglin.framework.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public class FileUtils {

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
