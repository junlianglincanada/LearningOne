package com.example.junlianglin.learningone.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycaretaker.db";

    private static final int VERSION = 9;
    public static final String TABLE_T_USER = "T_USER";
    public static final String TABLE_T_IMAGE = "T_IMAGE";
    public static final String TABLE_T_DICTIONARY = "T_DICTIONARY";
    public static final String TABLE_T_BUILDING = "T_BUILDING";
    public static final String TABLE_T_UNIT = "T_UNIT";
    public static final String TABLE_T_FORM = "T_FORM";
    public static final String TABLE_T_FIELDCATEGORY = "T_FIELDCATEGORY";
    public static final String TABLE_T_FORMFIELD = "T_FORMFIELD";

    public static final String TABLE_T_SUBMISSION = "T_SUBMISSION";
    public static final String TABLE_T_SUBMISSIONCATEGORY = "T_SUBMISSIONCATEGORY";
    public static final String TABLE_T_SUBMISSIONFIELD = "T_SUBMISSIONFIELD";

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

        String dictionaryTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_DICTIONARY + "(typename varchar(10),id integer,value varchar(30))";

        sqLiteDatabase.execSQL(dictionaryTableSQL);

        String buildingTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_BUILDING + "(id integer,name varchar(10),address varchar(500))";

        sqLiteDatabase.execSQL(buildingTableSQL);

        String unitTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_UNIT
                + "(id integer,buildingid integer,name varchar(10),unittype varchar(200),unitstatus varchar(20)," +
                " moveindate datetime,moveoutdate datetime)";

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



        String submissionTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_SUBMISSION
                + "(id integer,reportnumber integer,status varchar(20),rentaltype varchar(20),buildingaddress varchar(50),"
                + "buildingunitname varchar(20),tenantname varchar(30),moveindate datetime,moveoutdate datetime,"
                + "tenantemail varchar(30),tenantphonenumber varchar(20),tenantonsite boolean,inspectdate datetime,"
                + "createuser varchar(30),confirmeddate datetime,confirmeduser varchar(30),"
                + "firstwitnessemail varchar(30),emailnotificationdate datetime,forwardingaddress varchar(30))";

        sqLiteDatabase.execSQL(submissionTableSQL);



        String submissionCategoryTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_SUBMISSIONCATEGORY
                + "(id integer,submissionid integer,categoryname varchar(50),skipped boolean)";

        sqLiteDatabase.execSQL(submissionCategoryTableSQL);



        /*String submissionFieldTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_T_SUBMISSIONFIELD
                + "(id integer,categoryid integer,formfieldid integer,formfieldname varchar(50),status varchar(20),"
                + " workingrequested)";

        sqLiteDatabase.execSQL(submissionFieldTableSQL);*/

        onCreate(sqLiteDatabase);


    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

}
