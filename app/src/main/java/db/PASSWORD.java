package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                info = info + execSQL(line);
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

    public String felipedelosh(){

        String info = "Cargando la primera info\n";
        int rows = 0;

        try{
            InputStreamReader reader = new InputStreamReader(context.openFileInput("felipedelosh.txt"));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null){
                info = info + execSQL(line);
                line = br.readLine();
                rows = rows + 1;
            }
            br.close();
            reader.close();
            info = "Trate de insertar\n"+"Registros: "+Integer.toString(rows);
        }catch (Exception e){
            info = "No me fue posible cargar los datos\n";
        }

        return info;
    }

    public String execSQL(String sql){
        String info = "";
        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();
            db.execSQL(sql);
            return info;
        }catch (Exception e){
            return "Error: "+sql + "\n";
        }
    }

    public String restore(){

        String info = "restore DBB\n";

        try {

            InputStreamReader reader = new InputStreamReader(context.openFileInput("BACKUP.sql"));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            int rows = 0;
            int erros = 0;
            while (line != null){
                rows = rows + 1;
                if(!execSQL(line).equals("")){
                    erros = erros + 1;
                }
                line = br.readLine();
            }
            br.close();
            reader.close();



            if(erros>0){
                info = info + "Databse read and restore.\nErrors: "+erros;
            }else{
                info = info + "Databse read and restore.\nInserted rows: "+rows;
            }
        }catch (Exception e){
            info = "Error 404";
        }



        return info;
    }

    public String backupDatabase(){
        String info = "Building DB\n";
        List<String> listOfTables = new ArrayList<>();
        String BACKUP = "";
        int counterTables = 0;
        int counterRows = 0;

        try{
            //create a .sql to backup
            OutputStreamWriter newFile = new OutputStreamWriter(context.openFileOutput("BACKUP.sql", Context.MODE_PRIVATE));

            //Create a consult to extract table names
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

            if(c.moveToFirst()){
                while (!c.isAfterLast()){
                    counterTables = counterTables + 1;
                    String tableName = c.getString(0);

                    //Save only database tables
                    if(tableName.split("_")[0].equals("t")){
                        listOfTables.add(tableName);
                    }

                    c.moveToNext();
                }

            }


            int NroTables = listOfTables.size();

            if(NroTables>0){
                //Extract info of all tables


                for(int i=0;i<NroTables;i++){

                    //Only a tables with information

                    List<String> information = getTableTYPEandData(listOfTables.get(i));
                    if(information.size()>0){

                        String BACKUPHEAD = "INSERT INTO " + listOfTables.get(i) + " (";

                        //Get table column names
                        c = db.rawQuery("SELECT name FROM PRAGMA_TABLE_INFO("+"\'"+listOfTables.get(i)+"\')", null);
                        List<String> columnNames = new ArrayList<>();
                        if(c.moveToFirst()){
                            while (!c.isAfterLast()){
                                columnNames.add(c.getString(0));
                                c.moveToNext();
                            }
                        }
                        String BACKUPVALUES = BACKUPHEAD + columnNames.toString().replace("[", "").replace("]", "") + ") VALUES (";

                        //Put Values
                        String values = "";
                        for(int j=0;j<information.size();j++){
                            counterRows = counterRows + 1;
                            values = information.get(j);
                            BACKUP = BACKUP + BACKUPVALUES + values + ");\n";
                        }
                    }


                }

                c.close();

                newFile.write(BACKUP);
                newFile.flush();
                newFile.close();
                info = info + "create BACKUP.sql\n";
                info = info + "tables:"+counterTables+"\n";
                info = info + "rows:"+counterRows+"\n";

            }else{
                info = "Error 404";
            }


        }catch (Exception e){
            info = "Error creating backup.sql";
        }


        return info;
    }

    public List<String> getTableTYPEandData(String tableName){

        List<String> info = new ArrayList<>();

        try{

            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + tableName;
            //information.add(sql);
            Cursor getValues = db.rawQuery(sql, null);



            while (getValues.moveToNext()) {
                String data = "";
                List<String> temp = new ArrayList<>();
                for(int i=0;i<getValues.getColumnCount();i++){


                    switch (getValues.getType(i))  {
                        case Cursor.FIELD_TYPE_INTEGER:
                            temp.add(String.valueOf(getValues.getInt(i)));
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            temp.add("\'"+getValues.getString(i)+"\'");
                            break;
                    }
                }

                info.add(temp.toString().replace("[", "").replace("]", ""));
            }

            getValues.close();


        }catch (Exception e){

        }


        return info;
    }


    public String eraseDatabase(){
        String info = "Erase database... \n";

        boolean createConect = false;

        DatabaseController databaseController;
        SQLiteDatabase db;

        try{
            databaseController = new DatabaseController(context);
            db = databaseController.getWritableDatabase();
            createConect = true;
            info = info + "Conecte to database: \n";
        }catch (Exception e){
            createConect = false;
            info = "Error 404 :( \n";
        }

        if(createConect){

            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PROFILE);
                info = info + "FORMAT: " + TABLE_PROFILE + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PROFILE + "\n";
            }



            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_DRUGS);
                info = info + "FORMAT: " + TABLE_DRUGS + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_DRUGS + "\n";
            }



            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_FEELINGS);
                info = info + "FORMAT: " + TABLE_FEELINGS + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_FEELINGS + "\n";
            }

            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_ACTIVITIES);
                info = info + "FORMAT: " + TABLE_ACTIVITIES + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_ACTIVITIES + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_ACTIVITIES);
                info = info + "FORMAT: " + TABLE_ACTIVITIES + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_ACTIVITIES + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_PAGE_DIARY);
                info = info + "FORMAT: " + TABLE_PERSONAL_PAGE_DIARY + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_PAGE_DIARY + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_DREAM_DIARY);
                info = info + "FORMAT: " + TABLE_PERSONAL_DREAM_DIARY + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_DREAM_DIARY + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_GRATITUDE_DIARY);
                info = info + "FORMAT: " + TABLE_PERSONAL_GRATITUDE_DIARY + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_GRATITUDE_DIARY + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_DRUGS_COUNTER);
                info = info + "FORMAT: " + TABLE_PERSONAL_DRUGS_COUNTER + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_DRUGS_COUNTER + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_DRUGS_DIARY);
                info = info + "FORMAT: " + TABLE_DRUGS_DIARY + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_DRUGS_DIARY + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_DAY_TIME_DISTRIBUTION);
                info = info + "FORMAT: " + TABLE_DAY_TIME_DISTRIBUTION + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_DAY_TIME_DISTRIBUTION + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_FEELING_COUNTER);
                info = info + "FORMAT: " + TABLE_PERSONAL_FEELING_COUNTER + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_FEELING_COUNTER + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_BOX_LITLE);
                info = info + "FORMAT: " + TABLE_PERSONAL_BOX_LITLE + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_BOX_LITLE + "\n";
            }


            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_BOX_BIG);
                info = info + "FORMAT: " + TABLE_PERSONAL_BOX_BIG + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_BOX_BIG + "\n";
            }

            try {
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_PERSONAL_ECONOMY_TACCOUNTS);
                info = info + "FORMAT: " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + "\n";

            }catch (Exception e){
                info = info + "Error 404: " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + "\n";
            }

        }

        return info;
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

            try{
                databaseController = new DatabaseController(context);
                db = databaseController.getWritableDatabase();
                String sql = "SELECT COUNT(*) FROM " + TABLE_PERSONAL_ECONOMY_TACCOUNTS;
                Cursor getValues = db.rawQuery( sql, null);
                int value = 0;
                while(getValues.moveToNext()){
                    value = getValues.getInt(0);
                }
                info = info + TABLE_PERSONAL_ECONOMY_TACCOUNTS + ">>" + Integer.toString(value)+"\n";
                getValues.close();
            }catch (Exception e){
                info = info + TABLE_PERSONAL_ECONOMY_TACCOUNTS + ">>Error\n";
            }

        }

        return info;
    }
}
