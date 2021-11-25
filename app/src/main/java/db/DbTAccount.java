package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

public class DbTAccount extends DatabaseController {


    Context context;

    public DbTAccount(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertTAccount(String timeStamp, int id, String concept, int debit, int credit){

        long idT = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStamp);
            values.put("id", id);
            values.put("concept", concept);
            values.put("debit", debit);
            values.put("credit", credit);

            idT = db.insert(TABLE_PERSONAL_ECONOMY_TACCOUNTS, null, values);

        }catch (Exception e){

        }

        return idT;
    }



}
