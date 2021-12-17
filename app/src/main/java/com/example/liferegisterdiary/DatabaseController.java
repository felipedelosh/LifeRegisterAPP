package com.example.liferegisterdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class DatabaseController extends SQLiteOpenHelper implements Serializable {


    //To manage database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lifeRegisterDiary.db";

    //To tables manage
    public static final String TABLE_PROFILE = "t_profile";
    public static final String TABLE_DRUGS = "t_drugs";
    public static final String TABLE_FEELINGS = "t_feelings";
    public static final String TABLE_ACTIVITIES = "t_activities";
    public static final String TABLE_PERSONAL_PAGE_DIARY = "t_personal_page_diary";
    public static final String TABLE_PERSONAL_DREAM_DIARY = "t_personal_dream_diary";
    public static final String TABLE_PERSONAL_GRATITUDE_DIARY = "t_personal_gratitude_diary";
    public static final String TABLE_PERSONAL_DRUGS_COUNTER = "t_personal_drugs_counter";
    public static final String TABLE_DRUGS_DIARY = "t_drugs_diary";
    public static final String TABLE_DAY_TIME_DISTRIBUTION = "t_day_time_distribution";
    public static final String TABLE_PERSONAL_FEELING_COUNTER = "t_personal_feeling_counter";
    public static final String TABLE_PERSONAL_BOX_LITLE = "t_box_litle";
    public static final String TABLE_PERSONAL_BOX_BIG = "t_box_big";
    public static final String TABLE_PERSONAL_ECONOMY_TACCOUNTS = "t_economy_t_account";
    public static final String TABLE_NOTIFICATIONS = "t_notifications";
    public static final String TABLE_EVALUATEDAY110 = "t_evaluate_day";
    public static final String TABLE_EVALUATE_ACTIVITY_YESORNOT = "t_enjoy_activities_yesornot";



    public DatabaseController(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_PROFILE + "(" +
                "username TEXT NOT NULL," +
                "sex TEXT NOT NULL," +
                "yearBirthDate INTEGER NOT NULL," +
                "monthBirthDate INTEGER NOT NULL,"+
                "dayBirthDate INTEGER NOT NULL" +
                ")");

        db.execSQL("create table if not exists " + TABLE_DRUGS+ "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "drugName TEXT NOT NULL UNIQUE" +
                ")");

        db.execSQL("create table if not exists " + TABLE_ACTIVITIES+ "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "activityName TEXT NOT NULL UNIQUE" +
                ")");

        db.execSQL("create table if not exists " + TABLE_FEELINGS+ "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "feelingName TEXT NOT NULL UNIQUE" +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_PAGE_DIARY + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pageName TEXT NOT NULL," +
                "year INTEGER NOT NULL," +
                "timeStamp TEXT NOT NULL," +
                "history TEXT NOT NULL" +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_DREAM_DIARY + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dreamName TEXT NOT NULL," +
                "year INTEGER NOT NULL," +
                "timeStamp TEXT NOT NULL," +
                "history TEXT NOT NULL" +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_GRATITUDE_DIARY + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "history TEXT NOT NULL," +
                "timeStamp TEXT NOT NULL" +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_DRUGS_COUNTER + "(" +
                "timeStampH TEXT NOT NULL," +
                "drugName TEXT NOT NULL," +
                "PRIMARY KEY (timeStampH, drugName) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_DRUGS_DIARY + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "timeStampH TEXT NOT NULL," +
                "drugName TEXT NOT NULL," +
                "detonating TEXT NOT NULL," +
                "result TEXT NOT NULL" +
                ")");


        db.execSQL("create table if not exists " + TABLE_DAY_TIME_DISTRIBUTION + "(" +
                "timeStamp TEXT NOT NULL," +
                "hour TEXT NOT NULL," +
                "activity TEXT NOT NULL," +
                "PRIMARY KEY (timeStamp, hour) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_FEELING_COUNTER + "(" +
                "timeStamp TEXT NOT NULL," +
                "feelingName TEXT NOT NULL," +
                "PRIMARY KEY (timeStamp) " +
                ")");


        db.execSQL("create table if not exists " + TABLE_PERSONAL_BOX_LITLE + "(" +
                "timeStampH TEXT NOT NULL," +
                "money INTEGER NOT NULL," +
                "PRIMARY KEY (timeStampH) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_BOX_BIG + "(" +
                "timeStampH TEXT NOT NULL," +
                "money INTEGER NOT NULL," +
                "PRIMARY KEY (timeStampH) " +
                ")");


        db.execSQL("create table if not exists " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + "(" +
                "timeStamp TEXT NOT NULL," +
                "id INTEGER NOT NULL," +
                "concept TEXT NOT NULL," +
                "debit INTEGER NOT NULL," +
                "credit INTEGER NOT NULL," +
                "PRIMARY KEY (timeStamp, id) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_NOTIFICATIONS + "(" +
                "name TEXT NOT NULL," +
                "lasttime NOT NULL," +
                "PRIMARY KEY (name) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_EVALUATEDAY110 + "(" +
                "timeStamp TEXT NOT NULL," +
                "qualify INTEGER NOT NULL," +
                "PRIMARY KEY (timeStamp) " +
                ")");

        db.execSQL("create table if not exists " + TABLE_EVALUATE_ACTIVITY_YESORNOT + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nameActivity TEXT NOT NULL," +
                "timeStamp TEXT NOT NULL," +
                "response TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop all tables
        db.execSQL("DROP TABLE " + TABLE_PROFILE);


        onCreate(db);
    }




    public String healt(){
        String txt = "Estoy en el controlador de base de datos";
        return txt + "";
    }
}
