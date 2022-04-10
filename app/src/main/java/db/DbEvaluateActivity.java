package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

public class DbEvaluateActivity  extends DatabaseController {


    private transient Context context;

    public DbEvaluateActivity(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertEvaluateActivityYESORNOT(String nameActivity, String timeStamp, String response) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nameActivity", nameActivity);
            values.put("timeStamp", timeStamp);
            values.put("response", response);

            id = db.insert(TABLE_EVALUATE_ACTIVITY_YESORNOT, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }


    public String getMostAprovedActivity(){
        String activity = "";

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "select nameActivity, count(response) from " + TABLE_EVALUATE_ACTIVITY_YESORNOT + " where response = 'yes' group by nameActivity order by count(response) DESC limit 3";
            Cursor getValues = db.rawQuery( sql, null);

            while(getValues.moveToNext()){
                activity = activity + "-" + getValues.getString(0) + " ";
            }

        } catch (Exception e){
            activity = "???";
        }


        return activity;
    }


}
