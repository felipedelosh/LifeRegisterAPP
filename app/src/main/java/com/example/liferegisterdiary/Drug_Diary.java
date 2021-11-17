package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DbDrugsDiary;

public class Drug_Diary extends AppCompatActivity {

    private EditText txt_Title_drug_diary;
    private EditText txt_Detonating_drug_diary;
    private EditText txt_Result_drug_diary;
    private Button btnSaveDrugDiary;

    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_diary);

        timeController = new TimeController();

        txt_Title_drug_diary = findViewById(R.id.txt_Title_drug_diary);
        txt_Detonating_drug_diary = findViewById(R.id.txt_Detonating_drug_diary);
        txt_Result_drug_diary  = findViewById(R.id.txt_Result_drug_diary);



        setUpView();
    }


    private void setUpView(){

        btnSaveDrugDiary = findViewById(R.id.btnSaveDrugDiary);
        btnSaveDrugDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String timeStampH = timeController.timeStampH();
                String drugName = txt_Title_drug_diary.getText().toString().trim();
                String detonating = txt_Detonating_drug_diary.getText().toString().trim();
                String result = txt_Result_drug_diary.getText().toString().trim();

                if(validateText(drugName)&&validateText(detonating)&&validateText(result)){

                    DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(Drug_Diary.this);

                    Long id = dbDrugsDiary.insertDrugDiaryNote(timeStampH,drugName, detonating, result);

                    if(id > 0){
                        Toast.makeText(Drug_Diary.this, "History Save", Toast.LENGTH_LONG).show();
                        txt_Title_drug_diary.setText("");
                        txt_Detonating_drug_diary.setText("");
                        txt_Result_drug_diary.setText("");
                    }else{
                        Toast.makeText(Drug_Diary.this, "Dont save wait a moment...", Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

    }

    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }
}