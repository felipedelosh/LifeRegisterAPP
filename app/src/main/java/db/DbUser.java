package db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import models.User;

/*
* This is create top inser a user information
* */
public class DbUser extends DatabaseController {

    private transient Context context;

    public DbUser(@Nullable Context context){
        super(context);
        this.context = context;
    }

    /*
    * Return if user is register
    * */
    public boolean userIsregisted(){
        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();
            Cursor getUser = db.rawQuery("SELECT * FROM " + TABLE_PROFILE, null);
            int regNr = getUser.getCount();
            getUser.close();
            return regNr > 0;
        }catch (Exception e){
            return false;
        }
    }

    /*
    * name,sex,year brith date, month birth fate, day birth date
    * */
    public String getUserInformation(){
        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            Cursor getUser = db.rawQuery("SELECT * FROM " + TABLE_PROFILE + " LIMIT 1 ", null);
            getUser.moveToFirst();
            String usernameInfo = "";
            usernameInfo = "name:"+getUser.getString(0);
            usernameInfo = usernameInfo + ",sex:"+getUser.getString(1);
            usernameInfo = usernameInfo + ",year:"+getUser.getInt(2);
            usernameInfo = usernameInfo + ",month:"+getUser.getInt(3);
            usernameInfo = usernameInfo + ",day:"+getUser.getInt(4);

            getUser.close();
            return usernameInfo;
        }catch (Exception e){
            return "";
        }
    }

    /*
     * model User()
     * */
    public User getUser(){
        User user = null;
        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            Cursor getUser = db.rawQuery("SELECT * FROM " + TABLE_PROFILE + " LIMIT 1 ", null);
            getUser.moveToFirst();

            user = new User(getUser.getString(0),
                    getUser.getString(1),
                    getUser.getInt(2),
                    getUser.getInt(3),
                    getUser.getInt(4));

            getUser.close();
            return user;
        }catch (Exception e){
            return user;
        }
    }

    public long editUser(String username, String sex, int yearBirthDate, int monthBirthDate, int dayBirthDate){

        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();


            //Drop all table user
            String sql = "DELETE FROM " + TABLE_PROFILE;
            db.execSQL(sql);

            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("sex", sex);
            values.put("yearBirthDate", yearBirthDate);
            values.put("monthBirthDate", monthBirthDate);
            values.put("dayBirthDate", dayBirthDate);



            id = db.insert(TABLE_PROFILE, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;

    }


    public long insertUser(String username, String sex, int yearBirthDate, int monthBirthDate, int dayBirthDate){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("sex", sex);
            values.put("yearBirthDate", yearBirthDate);
            values.put("monthBirthDate", monthBirthDate);
            values.put("dayBirthDate", dayBirthDate);

            id = db.insert(TABLE_PROFILE, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }
}
