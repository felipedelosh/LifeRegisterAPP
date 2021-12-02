package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class DbDrugsDiary extends DatabaseController {


    private transient Context context;


    public DbDrugsDiary(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /*
    * Insert only drug name
    * */
    public long insertDrug(String drugName){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("drugName", drugName);

            id = db.insert(TABLE_DRUGS, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }

    public long insertDrugDiaryNote(String timeStampH, String drugName, String detonating, String result){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("drugName", drugName);
            values.put("detonating", detonating);
            values.put("result", result);

            id = db.insert(TABLE_DRUGS_DIARY, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }



    public List<String> getDrugsList(){

        List<String> information = new ArrayList<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_DRUGS;
            Cursor getDrugs = db.rawQuery( sql, null);

            while(getDrugs.moveToNext()){
                String nameDrug = "";
                nameDrug = getDrugs.getString(1);
                information.add(nameDrug);
            }

            getDrugs.close();
            return information;
        }catch (Exception e){
            return information;
        }

    }

    public long insertDrugCount(String timeStampH, String drugNamne){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("drugName", drugNamne);

            id = db.insert(TABLE_PERSONAL_DRUGS_COUNTER, null, values);

        }catch (Exception e){
            //Do nothing
        }

        return id;
    }


}
