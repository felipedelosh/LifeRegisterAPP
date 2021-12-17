package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;
import com.example.liferegisterdiary.Economy;

public class DbQualifyDay extends DatabaseController {

    private transient Context context;

    public DbQualifyDay(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertDayQualify(String timeStamp, int qualify) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStamp);
            values.put("qualify", qualify);

            id = db.insert(TABLE_EVALUATEDAY110, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }

    public boolean todayYouQuestion(String timeStamp){

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();
            String sql = "SELECT * FROM " + TABLE_EVALUATEDAY110 + " WHERE timeStamp = \'"+timeStamp+"\'";

            Cursor getInfo = db.rawQuery(sql, null);

            return getInfo.getCount() > 0;
        }catch (Exception e){
            return false;
        }
    }


}
