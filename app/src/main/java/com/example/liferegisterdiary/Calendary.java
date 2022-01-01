package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Calendary extends AppCompatActivity {


    private Spinner spinnerMount;
    private Spinner spnViewOptions;
    private TimeController timeController;
    private ArrayList<Button> btnsDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendary);

        timeController = new TimeController();
        spinnerMount = findViewById(R.id.spinnerMount);
        setSpinnerMonthsOptions();
        spnViewOptions = findViewById(R.id.spnViewOptions);
        setSpinnerViewOPtions();


        btnsDays = new ArrayList<>();

        loadAllButtons();
        putCurrentSpinnerDATE();


        spinnerMount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               refeshDays();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });

        spnViewOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showQuerySelection(spnViewOptions.getSelectedItemPosition());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });


    }

    public void setSpinnerMonthsOptions(){
        List<String> months = timeController.getCurrentMonths();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, months);
        spinnerMount.setAdapter(adapter);
    }

    public void setSpinnerViewOPtions(){
        String [] snpOptions = getResources().getStringArray(R.array.spinnerCalendarViewOptions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, snpOptions);
        spnViewOptions.setAdapter(adapter);
    }


    public void loadAllButtons(){
        Button btnMonth01 = findViewById(R.id.btnMonth01);
        btnsDays.add(btnMonth01);
        Button btnMonth02 = findViewById(R.id.btnMonth02);
        btnsDays.add(btnMonth02);
        Button btnMonth03 = findViewById(R.id.btnMonth03);
        btnsDays.add(btnMonth03);
        Button btnMonth04 = findViewById(R.id.btnMonth04);
        btnsDays.add(btnMonth04);
        Button btnMonth05 = findViewById(R.id.btnMonth05);
        btnsDays.add(btnMonth05);
        Button btnMonth06 = findViewById(R.id.btnMonth06);
        btnsDays.add(btnMonth06);
        Button btnMonth07 = findViewById(R.id.btnMonth07);
        btnsDays.add(btnMonth07);
        Button btnMonth08 = findViewById(R.id.btnMonth08);
        btnsDays.add(btnMonth08);
        Button btnMonth09 = findViewById(R.id.btnMonth09);
        btnsDays.add(btnMonth09);
        Button btnMonth10 = findViewById(R.id.btnMonth10);
        btnsDays.add(btnMonth10);
        Button btnMonth11 = findViewById(R.id.btnMonth11);
        btnsDays.add(btnMonth11);
        Button btnMonth12 = findViewById(R.id.btnMonth12);
        btnsDays.add(btnMonth12);
        Button btnMonth13 = findViewById(R.id.btnMonth13);
        btnsDays.add(btnMonth13);
        Button btnMonth14 = findViewById(R.id.btnMonth14);
        btnsDays.add(btnMonth14);
        Button btnMonth15 = findViewById(R.id.btnMonth15);
        btnsDays.add(btnMonth15);
        Button btnMonth16 = findViewById(R.id.btnMonth16);
        btnsDays.add(btnMonth16);
        Button btnMonth17 = findViewById(R.id.btnMonth17);
        btnsDays.add(btnMonth17);
        Button btnMonth18 = findViewById(R.id.btnMonth18);
        btnsDays.add(btnMonth18);
        Button btnMonth19 = findViewById(R.id.btnMonth19);
        btnsDays.add(btnMonth19);
        Button btnMonth20 = findViewById(R.id.btnMonth20);
        btnsDays.add(btnMonth20);
        Button btnMonth21 = findViewById(R.id.btnMonth21);
        btnsDays.add(btnMonth21);
        Button btnMonth22 = findViewById(R.id.btnMonth22);
        btnsDays.add(btnMonth22);
        Button btnMonth23 = findViewById(R.id.btnMonth23);
        btnsDays.add(btnMonth23);
        Button btnMonth24 = findViewById(R.id.btnMonth24);
        btnsDays.add(btnMonth24);
        Button btnMonth25 = findViewById(R.id.btnMonth25);
        btnsDays.add(btnMonth25);
        Button btnMonth26 = findViewById(R.id.btnMonth26);
        btnsDays.add(btnMonth26);
        Button btnMonth27 = findViewById(R.id.btnMonth27);
        btnsDays.add(btnMonth27);
        Button btnMonth28 = findViewById(R.id.btnMonth28);
        btnsDays.add(btnMonth28);
        Button btnMonth29 = findViewById(R.id.btnMonth29);
        btnsDays.add(btnMonth29);
        Button btnMonth30 = findViewById(R.id.btnMonth30);
        btnsDays.add(btnMonth30);
        Button btnMonth31 = findViewById(R.id.btnMonth31);
        btnsDays.add(btnMonth31);

        for(int i=0;i<btnsDays.size();i++){
            btnsDays.get(i).setText(String.valueOf(i+1));
        }

    }

    public void refeshDays(){
        String monthName = spinnerMount.getSelectedItem().toString().trim();
        int daysNumber = timeController.getNumberDaysOfMounthX(monthName);

        if(daysNumber == 31){
            btnsDays.get(28).setVisibility(View.VISIBLE);
            btnsDays.get(29).setVisibility(View.VISIBLE);
            btnsDays.get(30).setVisibility(View.VISIBLE);
        }


        if(daysNumber == 30){
            btnsDays.get(28).setVisibility(View.VISIBLE);
            btnsDays.get(29).setVisibility(View.VISIBLE);
            btnsDays.get(30).setVisibility(View.INVISIBLE);
        }


        if(daysNumber == 28){
            btnsDays.get(28).setVisibility(View.INVISIBLE);
            btnsDays.get(29).setVisibility(View.INVISIBLE);
            btnsDays.get(30).setVisibility(View.INVISIBLE);
        }
    }

    public void putCurrentSpinnerDATE(){
        int month = timeController.getNumberOfCurrentMounth();
        spinnerMount.setSelection(month-1);
    }

    /***
     * Paint a days whith a tipe of selected
     * 0 -> Diary Entries
     * 1 -> Psicotropy
     * 2 -> Emotional State
     *
     * @param tipeOfView
     */
    public void showQuerySelection(int tipeOfView){
        if(tipeOfView == 1){

        }
    }

    /***
     *
     * @return
     */
    public int [] colors(){

        int [] color = {0,0,0};



        return color;
    }


}