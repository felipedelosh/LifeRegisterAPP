package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PASSWORD extends DatabaseController {

    Context context;

    public PASSWORD(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public String roboto(){

        String info = "roboto\n";
        int rows = 0;

        try{
            InputStreamReader reader = new InputStreamReader(context.openFileInput("roboto.txt"));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null){
                execSQL(line);
                line = br.readLine();
                rows = rows + 1;
            }
            br.close();
            reader.close();
            info = "Read roboto\n"+"Afeted Rows: "+Integer.toString(rows);
        }catch (Exception e){
            info = "No read\n";
        }

       return info;
    }

    public void execSQL(String sql){
        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();
            db.execSQL(sql);
        }catch (Exception e){

        }

    }
}
