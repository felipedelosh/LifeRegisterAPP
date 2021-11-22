package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Calendary extends AppCompatActivity {


    private Spinner spinnerMount;
    private TimeController timeController;
    private ArrayList<Button> btnsDays;

    private Button btn_mounth_01;
    private Button btn_mounth_02;
    private Button btn_mounth_03;
    private Button btn_mounth_04;
    private Button btn_mounth_05;
    private Button btn_mounth_06;
    private Button btn_mounth_07;
    private Button btn_mounth_08;
    private Button btn_mounth_09;
    private Button btn_mounth_10;
    private Button btn_mounth_11;
    private Button btn_mounth_12;
    private Button btn_mounth_13;
    private Button btn_mounth_14;
    private Button btn_mounth_15;
    private Button btn_mounth_16;
    private Button btn_mounth_17;
    private Button btn_mounth_18;
    private Button btn_mounth_19;
    private Button btn_mounth_20;
    private Button btn_mounth_21;
    private Button btn_mounth_22;
    private Button btn_mounth_23;
    private Button btn_mounth_24;
    private Button btn_mounth_25;
    private Button btn_mounth_26;
    private Button btn_mounth_27;
    private Button btn_mounth_28;
    private Button btn_mounth_29;
    private Button btn_mounth_30;
    private Button btn_mounth_31;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendary);

        timeController = new TimeController();
        spinnerMount = findViewById(R.id.spinnerMount);
        setSpinnerMonthsOptions();

        btnsDays = new ArrayList<>();

        loadAllButtons();


    }

    public void setSpinnerMonthsOptions(){

        List<String> months = timeController.getCurrentMonths();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        spinnerMount.setAdapter(adapter);

    }


    public void loadAllButtons(){
        btn_mounth_01 = findViewById(R.id.btn_mounth_01);
        btnsDays.add(btn_mounth_01);
        btn_mounth_02 = findViewById(R.id.btn_mounth_02);
        btnsDays.add(btn_mounth_02);
        btn_mounth_03 = findViewById(R.id.btn_mounth_03);
        btnsDays.add(btn_mounth_03);
        btn_mounth_04 = findViewById(R.id.btn_mounth_04);
        btnsDays.add(btn_mounth_04);
        btn_mounth_05 = findViewById(R.id.btn_mounth_05);
        btnsDays.add(btn_mounth_05);
        btn_mounth_06 = findViewById(R.id.btn_mounth_06);
        btnsDays.add(btn_mounth_06);
        btn_mounth_07 = findViewById(R.id.btn_mounth_07);
        btnsDays.add(btn_mounth_07);
        btn_mounth_08 = findViewById(R.id.btn_mounth_08);
        btnsDays.add(btn_mounth_08);
        btn_mounth_09 = findViewById(R.id.btn_mounth_09);
        btnsDays.add(btn_mounth_09);
        btn_mounth_10 = findViewById(R.id.btn_mounth_10);
        btnsDays.add(btn_mounth_10);
        btn_mounth_11 = findViewById(R.id.btn_mounth_11);
        btnsDays.add(btn_mounth_11);
        btn_mounth_12 = findViewById(R.id.btn_mounth_12);
        btnsDays.add(btn_mounth_12);
        btn_mounth_13 = findViewById(R.id.btn_mounth_13);
        btnsDays.add(btn_mounth_13);
        btn_mounth_14 = findViewById(R.id.btn_mounth_14);
        btnsDays.add(btn_mounth_14);
        btn_mounth_15 = findViewById(R.id.btn_mounth_15);
        btnsDays.add(btn_mounth_15);
        btn_mounth_16 = findViewById(R.id.btn_mounth_16);
        btnsDays.add(btn_mounth_16);
        btn_mounth_17 = findViewById(R.id.btn_mounth_17);
        btnsDays.add(btn_mounth_17);
        btn_mounth_18 = findViewById(R.id.btn_mounth_18);
        btnsDays.add(btn_mounth_18);
        btn_mounth_19 = findViewById(R.id.btn_mounth_19);
        btnsDays.add(btn_mounth_19);
        btn_mounth_20 = findViewById(R.id.btn_mounth_20);
        btnsDays.add(btn_mounth_20);
        btn_mounth_21 = findViewById(R.id.btn_mounth_21);
        btnsDays.add(btn_mounth_21);
        btn_mounth_22 = findViewById(R.id.btn_mounth_22);
        btnsDays.add(btn_mounth_22);
        btn_mounth_23 = findViewById(R.id.btn_mounth_23);
        btnsDays.add(btn_mounth_23);
        btn_mounth_24 = findViewById(R.id.btn_mounth_24);
        btnsDays.add(btn_mounth_24);
        btn_mounth_25 = findViewById(R.id.btn_mounth_25);
        btnsDays.add(btn_mounth_25);
        btn_mounth_26 = findViewById(R.id.btn_mounth_26);
        btnsDays.add(btn_mounth_26);
        btn_mounth_27 = findViewById(R.id.btn_mounth_27);
        btnsDays.add(btn_mounth_27);
        btn_mounth_28 = findViewById(R.id.btn_mounth_28);
        btnsDays.add(btn_mounth_28);
        btn_mounth_29 = findViewById(R.id.btn_mounth_29);
        btnsDays.add(btn_mounth_29);
        btn_mounth_30 = findViewById(R.id.btn_mounth_30);
        btnsDays.add(btn_mounth_30);
        btn_mounth_31 = findViewById(R.id.btn_mounth_31);
        btnsDays.add(btn_mounth_31);

        for(int i=0;i<btnsDays.size();i++){
            btnsDays.get(i).setText(String.valueOf(i+1));
        }

    }

    public void refeshDays(){
        String days = spinnerMount.getSelectedItem().toString().toString();

        int daysNumber = timeController.getNumberDaysOfMounthX(days);

        if(daysNumber == 31){
            btnsDays.get(29).setVisibility(View.VISIBLE);
            btnsDays.get(30).setVisibility(View.VISIBLE);
            btnsDays.get(31).setVisibility(View.VISIBLE);
        }


        if(daysNumber == 30){
            btnsDays.get(29).setVisibility(View.VISIBLE);
            btnsDays.get(30).setVisibility(View.VISIBLE);
            btnsDays.get(31).setVisibility(View.INVISIBLE);
        }


        if(daysNumber == 28){
            btnsDays.get(29).setVisibility(View.VISIBLE);
            btnsDays.get(30).setVisibility(View.INVISIBLE);
            btnsDays.get(31).setVisibility(View.INVISIBLE);
        }
    }





}