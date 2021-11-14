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


    Context context;


    public DbDrugsDiary(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertDrug(String drugName){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("drugName", drugName);

            id = db.insert(TABLE_DRUGS, null, values);

        }catch (Exception e){

        }

        return id;
    }

    public List<String> getDrugsList(){

        List<String> information = new ArrayList<String>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_DRUGS;
            //information.add(sql);
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

        }

        return id;
    }


}
