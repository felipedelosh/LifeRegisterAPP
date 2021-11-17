package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class DbTimeDistribution extends DatabaseController {

    Context context;

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

        }

        return id;
    }


    public List<String> getActivitiesList() {

        List<String> information = new ArrayList<String>();

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_ACTIVITIES;
            //information.add(sql);
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



}
