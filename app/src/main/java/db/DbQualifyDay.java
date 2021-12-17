package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

public class DbQualifyDay extends DatabaseController {

    private transient Context context;

    public DbQualifyDay(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertDayQualify(String timeStampH, int qualify) {

        long id = 0;

        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("timeStamp", timeStampH);
            values.put("qualify", qualify);

            id = db.insert(TABLE_EVALUATEDAY110, null, values);

        } catch (Exception e){
            //Do nothing
        }

        return id;
    }


}
