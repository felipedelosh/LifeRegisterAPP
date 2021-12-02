package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class DbDreamDiaryPage extends DatabaseController {

    private transient Context context;

    public DbDreamDiaryPage(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertDreamDiary(String pageName, int year, String timeStamp, String history){
        long id = 0;

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("dreamName", pageName);
            values.put("year", year);
            values.put("timeStamp", timeStamp);
            values.put("history", history);

            id = db.insert(TABLE_PERSONAL_DREAM_DIARY, null, values);

        }catch (Exception e){
            //Do nothing
        }
        return id;
    }

    public List<String> getPagesDreamDiary(String keyword){

        List<String> information = new ArrayList<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_PERSONAL_DREAM_DIARY + " WHERE dreamName = '" + keyword + "'";
            Cursor getPageDiary = db.rawQuery( sql, null);

            while(getPageDiary.moveToNext()){
                String history = "";
                history = getPageDiary.getString(4) + "\n\n" +
                        getPageDiary.getString(3) + "\n";

                information.add(history);
            }

            getPageDiary.close();
            return information;
        }catch (Exception e){
            return information;
        }

    }
}
