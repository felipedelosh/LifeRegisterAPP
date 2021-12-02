package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import db.DbDrugsDiary;

public class DrugDiaryCounter extends AppCompatActivity {

    private Spinner spinnerDrugsCounter;
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

        Button btnSaveDrugEvent = findViewById(R.id.btnSaveDrugEvent);
        btnSaveDrugEvent.setOnClickListener(v -> {
            if(validateText(spinnerDrugsCounter.getSelectedItem().toString().trim())){

                try{

                    String timeStampH = timeController.timeStampH();
                    DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(DrugDiaryCounter.this);
                    Long id = dbDrugsDiary.insertDrugCount(timeStampH, spinnerDrugsCounter.getSelectedItem().toString().trim());

                    if(id > 0){
                        Toast.makeText(DrugDiaryCounter.this, "Psichodelic Save", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(DrugDiaryCounter.this, "Not save Wait...", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    //Do nothing
                }

            }
        });

    }

    public void updateSpinnerDrugsList(){

        try {
            DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(DrugDiaryCounter.this);
            List<String> drugsList = dbDrugsDiary.getDrugsList();

            String [] spinerOptions = new String[drugsList.size()];
            for(int i=0;i<drugsList.size();i++){
                spinerOptions[i] = drugsList.get(i);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
            spinnerDrugsCounter.setAdapter(adapter);
        }catch (Exception e){
            //Do nothing
        }


    }

    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }
}