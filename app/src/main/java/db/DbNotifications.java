package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class DbNotifications extends DatabaseController {

    private transient Context context;

    public DbNotifications(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertNotification(String name, String lasttime) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("lasttime", lasttime);

            id = db.insert(TABLE_NOTIFICATIONS, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }

    public List<String> getNotificationList(){

        List<String> information = new ArrayList<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_NOTIFICATIONS;
            Cursor getData = db.rawQuery( sql, null);

            while(getData.moveToNext()){
                String name = "";
                name = getData.getString(0);
                information.add(name);
            }

            getData.close();
            return information;
        }catch (Exception e){
            return information;
        }

    }


    public String getLastTimeOfNotification(String name){
        String info = "";

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_NOTIFICATIONS + " where name=\'"+name+"\'";
            Cursor getInfoN = db.rawQuery(sql, null);

            getInfoN.moveToFirst();

            info = getInfoN.getString(1);
            getInfoN.close();
        }catch (Exception e){
            //do nothing
        }

        return info;
    }

    public boolean editNotification(String name, String newDate){
        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "UPDATE " + TABLE_NOTIFICATIONS + " SET lasttime = \'" + newDate + "\' WHERE name = \'" + name + "\'";
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
