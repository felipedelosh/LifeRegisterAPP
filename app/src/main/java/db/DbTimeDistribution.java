package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbTimeDistribution extends DatabaseController {

    private transient Context context;

    public DbTimeDistribution(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    /*
     * Insert only activity name
     * */
    public long insertActivity(String activityName){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("activityName", activityName);

            id = db.insert(TABLE_ACTIVITIES, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }

    /*
     * Insert activity in the day
     * */
    public long insertDiaryActivity(String timeStamp, String hour, String activity){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStamp);
            values.put("hour", hour);
            values.put("activity", activity);

            id = db.insert(TABLE_DAY_TIME_DISTRIBUTION, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }


    public List<String> getActivitiesList() {

        List<String> information = new ArrayList<>();

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_ACTIVITIES;
            Cursor getActivities = db.rawQuery(sql, null);

            while (getActivities.moveToNext()) {
                String nameActivities = "";
                nameActivities = getActivities.getString(1);
                information.add(nameActivities);
            }

            getActivities.close();
            return information;
        } catch (Exception e) {
            return information;
        }

    }


    /***
     * return <activity, #total, activity, #total, activity, #total...>
     *
     * */
    public HashMap<String, Integer> getDataInfo(String timeStamp){

        HashMap<String, Integer> information = new HashMap<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT activity, COUNT(*)  FROM " + TABLE_DAY_TIME_DISTRIBUTION + " WHERE timeStamp LIKE \'" + timeStamp + "%\' GROUP BY activity";

            Cursor getValues = db.rawQuery( sql, null);

            while(getValues.moveToNext()){
                String activity = getValues.getString(0);
                int value = getValues.getInt(1);
                information.put(activity, value);
            }

            return information;
        }catch (Exception e){
            return information;
        }




    }

}
