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

public class DbBox extends DatabaseController {

    private transient Context context;
    private static final String SELECT_ALL = "SELECT * FROM ";

    public DbBox(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertBoxLitleCount(String timeStampH, int money) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("money", money);

            id = db.insert(TABLE_PERSONAL_BOX_LITLE, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }

    public long insertBoxBigCount(String timeStampH, int money) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("money", money);

            id = db.insert(TABLE_PERSONAL_BOX_BIG, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }


    /*
     *
     * */
    public List<Integer> getAllCurrentValuesOfLitleBox(String timeStamp) {

        List<Integer> information = new ArrayList<>();

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = SELECT_ALL + TABLE_PERSONAL_BOX_LITLE + " WHERE timeStampH LIKE \'" + timeStamp + "%\'";

            Cursor getValues = db.rawQuery(sql, null);

            while (getValues.moveToNext()) {

                int value = getValues.getInt(1);
                information.add(value);
            }

            getValues.close();
            return information;
        } catch (Exception e) {
            return information;
        }

    }


    public List<Integer> getAllCurrentValuesOfBigBox(String timeStamp) {
        List<Integer> information = new ArrayList<>();

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = SELECT_ALL + TABLE_PERSONAL_BOX_BIG + " WHERE timeStampH LIKE \'" + timeStamp + "%\'";

            Cursor getValues = db.rawQuery(sql, null);

            while (getValues.moveToNext()) {

                int value = getValues.getInt(1);
                information.add(value);
            }

            getValues.close();
            return information;
        } catch (Exception e) {
            return information;
        }
    }


    public HashMap<String, Integer> getALLInfoBoxes(){

        HashMap<String, Integer> information = new HashMap<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = SELECT_ALL + TABLE_PERSONAL_BOX_LITLE;

            Cursor getValuesLitle = db.rawQuery(sql, null);

            while (getValuesLitle.moveToNext()) {

                String key = "LITLE:" + getValuesLitle.getString(0);
                int value = getValuesLitle.getInt(1);

                information.put(key, value);

            }

            getValuesLitle.close();

            sql = SELECT_ALL + TABLE_PERSONAL_BOX_BIG;

            Cursor getValuesBig = db.rawQuery(sql, null);

            while (getValuesBig.moveToNext()) {

                String key = "BIG:" + getValuesBig.getString(0);
                int value = getValuesBig.getInt(1);

                information.put(key, value);

            }

            getValuesBig.close();


            return information;
        }catch (Exception e){
            return information;
        }

    }



}
