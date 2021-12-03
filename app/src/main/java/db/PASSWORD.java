package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PASSWORD extends DatabaseController {

    private Context context;

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
        List<String> listOfTables = getAllTableNames();
        String sqlBACKUP = "";
        int counterTables = listOfTables.size();
        int counterRows = 0;

        try{
            //create a .sql to backup
            OutputStreamWriter newFile = new OutputStreamWriter(context.openFileOutput("BACKUP.sql", Context.MODE_PRIVATE));

            //Create a consult to extract table names
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            int nroTables = listOfTables.size();

            if(nroTables>0){
                //Extract info of all tables

                for(int i=0;i<nroTables;i++){

                    //Only a tables with information
                    List<String> information = getTableTYPEandData(listOfTables.get(i));
                    if(information.size()>0){

                        String sqlBACKUPHEAD = "INSERT INTO " + listOfTables.get(i) + " (";

                        //Get table column names
                        Cursor c = db.rawQuery("SELECT name FROM PRAGMA_TABLE_INFO("+"\'"+listOfTables.get(i)+"\')", null);
                        List<String> columnNames = new ArrayList<>();
                        if(c.moveToFirst()){
                            while (!c.isAfterLast()){
                                columnNames.add(c.getString(0));
                                c.moveToNext();
                            }
                        }
                        String sqlBACKUPVALUES = sqlBACKUPHEAD + columnNames.toString().replace("[", "").replace("]", "") + ") VALUES (";

                        //Put Values
                        String values = "";
                        for(int j=0;j<information.size();j++){
                            counterRows = counterRows + 1;
                            values = information.get(j);
                            sqlBACKUP = sqlBACKUP + sqlBACKUPVALUES + values + ");\n";
                        }
                    }


                }

                newFile.write(sqlBACKUP);
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

            Cursor getValues = db.rawQuery(sql, null);



            while (getValues.moveToNext()) {
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
            //Do nothing
        }


        return info;
    }

    /****
     * Erase all rows in all tables of database
     * @return
     */
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

            List<String> allTables = getAllTableNames();

            for(int i=0;i<allTables.size();i++){
                try {
                    databaseController = new DatabaseController(context);
                    db = databaseController.getWritableDatabase();
                    db.execSQL("DELETE FROM " + allTables.get(i));
                    info = info + "FORMAT: " + allTables.get(i) + "\n";

                }catch (Exception e){
                    info = info + "Error 404: " + allTables.get(i) + "\n";
                }
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

            List<String> allTables = getAllTableNames();

            for(int i=0;i<allTables.size();i++){
                try{
                    databaseController = new DatabaseController(context);
                    db = databaseController.getWritableDatabase();
                    String sql = "SELECT COUNT(*) FROM " + allTables.get(i);
                    Cursor getValues = db.rawQuery( sql, null);
                    int value = 0;
                    while(getValues.moveToNext()){
                        value = getValues.getInt(0);
                    }
                    info = info + allTables.get(i) + ">>" + Integer.toString(value)+"\n";
                    getValues.close();
                }catch (Exception e){
                    info = info + allTables.get(i) + ">>Error\n";
                }
            }

        }

        return info;
    }

    /****
     * return all tables of database
     *
     * @return {"t_name, t_name, t_name..."}
     */
    public List<String> getAllTableNames(){
        List<String> tablesNames = new ArrayList<>();

        try{
            //Create a consult to extract table names
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

            if(c.moveToFirst()){
                while (!c.isAfterLast()){
                    String tableName = c.getString(0);


                    if(tableName.split("_")[0].equals("t")){
                        tablesNames.add(tableName);
                    }
                    c.moveToNext();
                }
            }
        }catch (Exception e){
            //Do nothing
        }

        return tablesNames;
    }
}
