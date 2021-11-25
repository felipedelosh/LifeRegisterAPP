package db;

import android.content.Context;
import android.database.Cursor;
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

    public String databaseCountRouts(){
        String info = "";
        boolean createConect = false;

        DatabaseController databaseController;
        SQLiteDatabase db;

        try{
            databaseController = new DatabaseController(context);
            db = databaseController.getWritableDatabase();
            createConect = true;
            info = "Database info: \n";
        }catch (Exception e){
            createConect = false;
            info = "Error 404 :( \n";
        }

        if(createConect){
            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PROFILE;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PROFILE + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PROFILE + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_DRUGS;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_DRUGS + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_DRUGS + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_FEELINGS;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_FEELINGS + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_FEELINGS + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_ACTIVITIES;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_ACTIVITIES + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_ACTIVITIES + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_PAGE_DIARY;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_PAGE_DIARY + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_PAGE_DIARY + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_DREAM_DIARY;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_DREAM_DIARY + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_DREAM_DIARY + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_GRATITUDE_DIARY;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_GRATITUDE_DIARY + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_GRATITUDE_DIARY + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_DRUGS_COUNTER;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_DRUGS_COUNTER + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_DRUGS_COUNTER + ">>Error\n";
            }


            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_DRUGS_DIARY;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_DRUGS_DIARY + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_DRUGS_DIARY + ">>Error\n";
            }


            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_DAY_TIME_DISTRIBUTION;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_DAY_TIME_DISTRIBUTION + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_DAY_TIME_DISTRIBUTION + ">>Error\n";
            }


            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_FEELING_COUNTER;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_FEELING_COUNTER + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_FEELING_COUNTER + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_BOX_LITLE;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_BOX_LITLE + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_BOX_LITLE + ">>Error\n";
            }

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_BOX_BIG;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_BOX_BIG + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_BOX_BIG + ">>Error\n";
            }


        }

        return info;
    }
}
