package com.example.junlianglin.learningone.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycaretaker.db";

    private static final int VERSION = 5;
    public static final String TABLE_T_USER = "T_USER";
    public static final String TABLE_T_IMAGE = "T_IMAGE";
    public static final String TABLE_T_DICTIONARY = "T_DICTIONARY";
    public static final String TABLE_T_BUILDING = "T_BUILDING";
    public static final String TABLE_T_UNIT = "T_UNIT";
    public static final String TABLE_T_FORM = "T_FORM";
    public static final String TABLE_T_FIELDCATEGORY = "T_FIELDCATEGORY";
    public static final String TABLE_T_FORMFIELD = "T_FORMFIELD";

    public SqliteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strSQL = "CREATE TABLE IF NOT EXISTS "
                         + TABLE_T_USER
                         + "(id integer primary key autoincrement,token varchar(50))";

        //String imageTableSQL = "CREATE TABLE IF NOT EXISTS "
        //        + TABLE_T_IMAGE + "(id integer primary key autoincrement,extension varchar(10),content blob,modifydate timestamp,createdate timestamp)";


        sqLiteDatabase.execSQL(strSQL);
        //sqLiteDatabase.execSQL(imageTableSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        String imageTableSQL = "CREATE TABLE IF NOT EXISTS "
                + TABLE_T_IMAGE + "(id integer primary key autoincrement,extension varchar(10),content blob,modifydate timestamp,createdate timestamp)";

        sqLiteDatabase.execSQL(imageTableSQL);

        String dictionaryTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_DICTIONARY + "(id integer primary key autoincrement,typename varchar(10),keyid integer,value varchar(30))";

        sqLiteDatabase.execSQL(dictionaryTableSQL);

        String buildingTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_BUILDING + "(id integer,name varchar(10),address varchar(500))";

        sqLiteDatabase.execSQL(buildingTableSQL);

        String unitTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_UNIT
                + "(id integer,buildingid integer,name varchar(10),unittype varchar(200),unitstatus varchar(20)," +
                " address varchar(500),moveindate datetime,moveoutdate datetime)";

        sqLiteDatabase.execSQL(unitTableSQL);

        String formTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_FORM
                + "(id integer,cityid integer,name varchar(10),startnumber int)";

        sqLiteDatabase.execSQL(formTableSQL);

        String formCategoryTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_FIELDCATEGORY
                + "(id integer,formid integer,code varchar(10),name varchar(50))";

        sqLiteDatabase.execSQL(formCategoryTableSQL);

        String formFieldTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_FORMFIELD
                + "(id integer,categoryid integer,name varchar(50),datatype varchar(20),orderby int)";

        sqLiteDatabase.execSQL(formFieldTableSQL);

        onCreate(sqLiteDatabase);


    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

}
