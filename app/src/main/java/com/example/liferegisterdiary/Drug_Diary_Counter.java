package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import db.DbDrugsDiary;

public class Drug_Diary_Counter extends AppCompatActivity {

    private Spinner spinnerDrugsCounter;
    private Button btnSaveDrugEvent;
    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_diary_counter);

        timeController = new TimeController();

        spinnerDrugsCounter = findViewById(R.id.spinnerDrugsCounter);
        updateSpinnerDrugsList();

        setUpView();
    }


    private void setUpView(){

        btnSaveDrugEvent = findViewById(R.id.btnSaveDrugEvent);
        btnSaveDrugEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateText(spinnerDrugsCounter.getSelectedItem().toString().trim())){

                    String timeStampH = timeController.timeStampH();
                    DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(Drug_Diary_Counter.this);
                    Long id = dbDrugsDiary.insertDrugCount(timeStampH, spinnerDrugsCounter.getSelectedItem().toString().trim());

                    if(id > 0){
                        Toast.makeText(Drug_Diary_Counter.this, "Psichodelic Save", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Drug_Diary_Counter.this, "Not save Wait...", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

    }

    public void updateSpinnerDrugsList(){

        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(Drug_Diary_Counter.this);
        List<String> drugsList = dbDrugsDiary.getDrugsList();

        String [] spinerOptions = new String[drugsList.size()];
        for(int i=0;i<drugsList.size();i++){
            spinerOptions[i] = drugsList.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerDrugsCounter.setAdapter(adapter);
    }

    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }
}