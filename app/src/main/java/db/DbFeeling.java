package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class DbFeeling extends DatabaseController {

    Context context;

    public DbFeeling(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertFeelingCount(String timeStamp, String feelingName){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStamp);
            values.put("feelingName", feelingName);

            id = db.insert(TABLE_PERSONAL_FEELING_COUNTER, null, values);

        }catch (Exception e){

        }

        return id;
    }


    /*
     * Insert only feeling name
     * */
    public long insertFeeling(String feelingName){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("feelingName", feelingName);

            id = db.insert(TABLE_FEELINGS, null, values);

        }catch (Exception e){

        }

        return id;
    }

    public List<String> getFeelingsList(){

        List<String> information = new ArrayList<String>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_FEELINGS;
            //information.add(sql);
            Cursor getFeelings = db.rawQuery( sql, null);

            while(getFeelings.moveToNext()){
                String nameFeeling = "";
                nameFeeling = getFeelings.getString(1);
                information.add(nameFeeling);
            }

            getFeelings.close();
            return information;
        }catch (Exception e){
            return information;
        }

    }


}