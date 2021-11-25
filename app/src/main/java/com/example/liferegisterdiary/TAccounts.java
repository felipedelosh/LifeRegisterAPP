package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Adapters.EconomyAdapter;
import Models.ItemTaccont;

public class TAccounts extends AppCompatActivity {

    private Spinner spinner_days;
    private Spinner spinner_mounth;
    private ListView listViewEconomy;


    //private EconomyAdapter economyAdapter;
    //private ArrayList <ItemTaccont> arrayTaccounts;
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
        //listViewEconomy = findViewById(R.id.listViewEconomy);

        putAMonthsInSpinner();
        //Put all days of current mounth
        refreshDaysOfSpinner();
        //Put currently mont in spinner
        spinner_mounth.setSelection(timeController.getNumberOfCurrentMounth());


        //Generate Taccount items
        //arrayTaccounts = new ArrayList<>();
        //economyAdapter = new EconomyAdapter(arrayTaccounts, context);
        //listViewEconomy.setAdapter(economyAdapter);
        //for(int i=0;i<10;i++){
        //    createNewTAccountsItem();
        //}

        spinner_mounth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               refreshDaysOfSpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    //public void createNewTAccountsItem(){
    //    ItemTaccont itemTaccont = new ItemTaccont(0, "", 0, 0);
    //    arrayTaccounts.add(itemTaccont);
    //}


    public void putAMonthsInSpinner(){
        spinner_mounth = findViewById(R.id.spinner_mounth);
        List<String> months = timeController.getCurrentMonths();
        ArrayAdapter <String> adapterSpinnerMounth = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        spinner_mounth.setAdapter(adapterSpinnerMounth);
        //Put a Mounth in spinner
        spinner_mounth.setSelection(timeController.getNumberOfCurrentMounth());
    }

    public void refreshDaysOfSpinner(){

        String nameMonth = spinner_mounth.getSelectedItem().toString().trim();
        int days = timeController.getNumberDaysOfMounthX(nameMonth);

        String [] spnerDays = new String[days];
        for(int i=0;i<days;i++){
            spnerDays[i] = String.valueOf(i+1);
        }

        ArrayAdapter <String> adapterSpinnerDays = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spnerDays);
        spinner_days.setAdapter(adapterSpinnerDays);

        //If currentle month put currentle day
        if(nameMonth == timeController.getCurrentMonth()){
            spinner_days.setSelection(timeController.getCurrentDayNumberOfMount()-1);
        }
    }


}