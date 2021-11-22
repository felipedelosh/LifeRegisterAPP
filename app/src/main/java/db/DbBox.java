package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

public class DbBox extends DatabaseController {

    Context context;

    public DbBox(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertBoxLitleCount(String timeStampH, int money){

        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("money", money);

            id = db.insert(TABLE_PERSONAL_BOX_LITLE, null, values);

        }catch (Exception e){

        }

        return id;
    }

    public long insertBoxBigCount(String timeStampH, int money){

        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStampH", timeStampH);
            values.put("money", money);

            id = db.insert(TABLE_PERSONAL_BOX_BIG, null, values);

        }catch (Exception e){

        }

        return id;
    }



}
