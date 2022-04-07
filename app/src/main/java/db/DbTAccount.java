package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbTAccount extends DatabaseController {


    private transient Context context;

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
            //Do nothing
        }

        return idT;
    }


    public List<String> getTAccountByTimeStamp(String timeStamp){

        List<String> information = new ArrayList<>();

        try{
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT * FROM " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + " WHERE timeStamp = \'" + timeStamp+"\'";
            Cursor getValues = db.rawQuery( sql, null);

            while(getValues.moveToNext()){

                String concept = getValues.getString(2);
                int debit = getValues.getInt(3);
                int credit = getValues.getInt(4);

                information.add(concept+";"+debit+";"+credit+";");

            }

            return information;
        }catch (Exception e){
            return information;
        }
    }

    /**
     * Casify all information into debit or credit an return
     *
     * debit
     * {"concep", +#}
     * credit
     * {"concep", -#}
     * */
    public HashMap<String, Integer> getTAccountsInfo(String timeStamp){

        HashMap<String, Integer> information = new HashMap<>();

        try{

            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT concept, SUM(debit), SUM(credit) FROM " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + " WHERE timeStamp LIKE \'" + timeStamp + "%\' GROUP BY concept";

            Cursor getValues = db.rawQuery( sql, null);
            String concept = "";
            int money = 0;
            while(getValues.moveToNext()){

                concept = getValues.getString(0);

                int temp = getValues.getInt(1);

                if(temp == 0){
                    money = - getValues.getInt(2);
                }else{
                    money = getValues.getInt(1);
                }

                information.put(concept, money);
            }

            return information;
        }catch (Exception e){
            return information;
        }
    }


    /***
     * Casify all information into debit or credit via detail
     * Example: travels, food ... movies...
     * @param deatil This is a concept to know
     * @return
     *      * debit
     *      * {"concep", +#}
     *      * credit
     *      * {"concep", -#}
     */
    public HashMap<String, Integer> getTAccountsByDetail(String deatil){

        HashMap<String, Integer> information = new HashMap<>();
        try{

            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "SELECT concept, debit, credit, timeStamp  FROM " + TABLE_PERSONAL_ECONOMY_TACCOUNTS + " WHERE concept LIKE \'%" + deatil + "%\'";

            Cursor getValues = db.rawQuery( sql, null);
            String concept = "";
            int money = 0;
            while(getValues.moveToNext()){

                concept = getValues.getString(0) + ":" + getValues.getString(3);

                int temp = getValues.getInt(1);

                if(temp == 0){
                    money = - getValues.getInt(2);
                }else{
                    money = getValues.getInt(1);
                }

                information.put(concept, money);
            }

            return information;
        }catch (Exception e){
            return information;
        }

    }


    /***
     * Return count of Taccounts registers
     * @return int
     */
    public int getTaccountReguistersCounter(){
        try {
            DatabaseController databaseController = new DatabaseController(context);
            SQLiteDatabase db = databaseController.getWritableDatabase();

            String sql = "select count(*) from " + TABLE_PERSONAL_ECONOMY_TACCOUNTS;
            Cursor getValues = db.rawQuery( sql, null);
            getValues.moveToFirst();

            return getValues.getInt(0);


        }catch (Exception e){
            return 0;
        }

    }




}
