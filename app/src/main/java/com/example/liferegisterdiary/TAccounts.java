package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Adapters.EconomyAdapter;
import Models.ItemTaccont;

public class TAccounts extends AppCompatActivity {

    private Spinner spinner_days;
    private Spinner spinner_mounth;
    private ListView listViewEconomy;


    private EconomyAdapter economyAdapter;
    private ArrayList <ItemTaccont> arrayTaccounts;
    private Context context;

    private TimeController timeController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taccounts);

        context = this;
        timeController = new TimeController();

        spinner_days = findViewById(R.id.spinner_days);
        spinner_mounth = findViewById(R.id.spinner_mounth);
        listViewEconomy = findViewById(R.id.listViewEconomy);

        //Put all days of current mounth
        setSpinnerDaysOfMount(timeController.getNumberOfCurrentMounth());
        //Put a day in spinner
        spinner_days.setSelection(timeController.getCurrentDayNumberOfMount()-1);


        spinner_mounth = findViewById(R.id.spinner_mounth);
        String [] spnerMon = new String[12];
        for(int i=0;i<12;i++){
            spnerMon[i] = timeController.getMonths()[i];
        }
        ArrayAdapter <String> adapterSpinnerMounth = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spnerMon);
        spinner_mounth.setAdapter(adapterSpinnerMounth);
        //Put a Mounth in spinner
        spinner_mounth.setSelection(timeController.getNumberOfCurrentMounth());

        //Generate Taccount items
        arrayTaccounts = new ArrayList<>();
        economyAdapter = new EconomyAdapter(arrayTaccounts, context);
        listViewEconomy.setAdapter(economyAdapter);
        for(int i=0;i<10;i++){
            createNewTAccountsItem();
        }
    }


    public void createNewTAccountsItem(){
        ItemTaccont itemTaccont = new ItemTaccont("", "", 0, 0);
        arrayTaccounts.add(itemTaccont);
    }

    /*
    * Enter a number of mounth enuary 0, february = 1 ...  december = 12
    * And the Spriner spr set a days of mount enuary = 31 february = 28 · Always Novenver = 30
    * */
    public void setSpinnerDaysOfMount(int mounth){
        spinner_days = findViewById(R.id.spinner_days);
        int allDayOfMounth = timeController.getDaysOfMounthX(mounth);
        String [] spnerDays = new String[allDayOfMounth];

        for(int i=0;i<allDayOfMounth;i++){
            spnerDays[i] = String.valueOf(i+1);
        }

        ArrayAdapter <String> adapterSpinnerDays = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spnerDays);
        spinner_days.setAdapter(adapterSpinnerDays);
    }
}