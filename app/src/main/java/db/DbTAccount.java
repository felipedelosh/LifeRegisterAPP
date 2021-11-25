package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.DatabaseController;

import java.util.HashMap;

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



}
