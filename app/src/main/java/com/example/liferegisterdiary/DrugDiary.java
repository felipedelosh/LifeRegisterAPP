package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DbDrugsDiary;

public class DrugDiary extends AppCompatActivity {

    private EditText txtTitleDrugDiary;
    private EditText txtDetonatingDrugDiary;
    private EditText txtResultDrugDiary;

    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_diary);

        timeController = new TimeController();

        txtTitleDrugDiary = findViewById(R.id.txtTitledrugdiary);
        txtDetonatingDrugDiary = findViewById(R.id.txtDetonatingDrugDiary);
        txtResultDrugDiary  = findViewById(R.id.txtResultDrugDiary);



        setUpView();
    }


    private void setUpView(){

        Button btnSaveDrugDiary = findViewById(R.id.btnSaveDrugDiary);
        btnSaveDrugDiary.setOnClickListener(v -> {

            String timeStampH = timeController.timeStampH();
            String drugName = txtTitleDrugDiary.getText().toString().trim();
            String detonating = txtDetonatingDrugDiary.getText().toString().trim();
            String result = txtResultDrugDiary.getText().toString().trim();

            if(validateText(drugName)&&validateText(detonating)&&validateText(result)){

                try {
                    DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(DrugDiary.this);

                    Long id = dbDrugsDiary.insertDrugDiaryNote(timeStampH,drugName, detonating, result);

                    if(id > 0){
                        Toast.makeText(DrugDiary.this, "History Save", Toast.LENGTH_LONG).show();
                        txtTitleDrugDiary.setText("");
                        txtDetonatingDrugDiary.setText("");
                        txtResultDrugDiary.setText("");
                    }else{
                        Toast.makeText(DrugDiary.this, "Dont save wait a moment...", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    //Do nothing
                }
            }
        });

    }

    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }
}