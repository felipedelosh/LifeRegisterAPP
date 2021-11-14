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
    public static final String TABLE_PERSONAL_PAGE_DIARY = "t_personal_page_diary";

    public DatabaseController(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a tables
        db.execSQL("create table if not exists " + TABLE_PROFILE + "(" +
                "username TEXT NOT NULL," +
                "sex TEXT NOT NULL," +
                "yearBirthDate INTEGER NOT NULL," +
                "monthBirthDate INTEGER NOT NULL,"+
                "dayBirthDate INTEGER NOT NULL" +
                ")");

        db.execSQL("create table if not exists " + TABLE_PERSONAL_PAGE_DIARY + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pageName TEXT NOT NULL," +
                "year INTEGER NOT NULL," +
                "timeStamp TEXT NOT NULL," +
                "history TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop all tables
        db.execSQL("DROP TABLE " + TABLE_PROFILE);


        onCreate(db);
    }

    public String healt(){
        return "Estoy en el controlador de base de datos";
    }
}
