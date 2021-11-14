package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

public class DbGratitudeHistory extends DatabaseController {

    Context context;


    public DbGratitudeHistory(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertGratirudeHistory(String timeStamp, String history){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStamp);
            values.put("history", history);

            id = db.insert(TABLE_PERSONAL_GRATITUDE_DIARY, null, values);

        }catch (Exception e){

        }
        return id;
    }


}
