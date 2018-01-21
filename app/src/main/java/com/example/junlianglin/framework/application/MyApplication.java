package com.example.junlianglin.framework.application;

import android.app.Application;
import android.util.DisplayMetrics;

import com.example.junlianglin.framework.exception.BaseExceptionHandler;
import com.example.junlianglin.framework.exception.LocalFileHandler;
import com.example.junlianglin.framework.utils.FileUtils;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

import java.io.File;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    public int screenWidth = 0;
    public int getScreenHeight = 0;

    public  static MyApplication getMyApplication(){
        if(myApplication==null)
            myApplication = new MyApplication();
        return  myApplication;
    }



    public DbManager db;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(applicationContext.getApplicationInfo().name) //设置数据库名，默认xutils.db
                .setDbDir(new File("/mnt/sdcard/")) //设置数据库路径，默认存储在app的私有目录
                .setDbVersion(2) //设置数据库的版本号
                .setDbOpenListener(new DbManager.DbOpenListener() { //设置数据库打开的监听
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能，对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() { //设置数据库更新的监听
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                })
                .setTableCreateListener(new DbManager.TableCreateListener() { //设置表创建的监听
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table){

                    }
                });
        File appFolder = new File(FileUtils.getDiskCacheDir(this) + "/log");
        if(!appFolder.exists())
            appFolder.mkdirs();

        myApplication = this;

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        getScreenHeight = displayMetrics.heightPixels;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getGetScreenHeight() {
        return getScreenHeight;
    }

    public void setGetScreenHeight(int getScreenHeight) {
        this.getScreenHeight = getScreenHeight;
    }
}
