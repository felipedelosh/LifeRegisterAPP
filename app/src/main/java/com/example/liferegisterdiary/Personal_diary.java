package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import db.DbPersonalDiaryPage;


public class Personal_diary extends AppCompatActivity {

    private Button saveDiaryPage;
    private Button loadDiaryPage;
    private EditText txtInsertTitlePageDiary;
    private EditText txtInsertHistoryPageDiary;

    //Need control not repeat information
    private String oldInformation;

    TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_diary);

        timeController = new TimeController();

        txtInsertTitlePageDiary = findViewById(R.id.txtInsertTitlePageDiary);
        txtInsertHistoryPageDiary = findViewById(R.id.txtInsertHistoryPageDiary);

        setUpView();
    }


    //Configure a button click
    private void setUpView(){
        saveDiaryPage = findViewById(R.id.saveDiaryPage);
        saveDiaryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateText(txtInsertTitlePageDiary.getText().toString()) && validateText(txtInsertHistoryPageDiary.getText().toString())){
                    DbPersonalDiaryPage dbPersonalDiaryPage = new DbPersonalDiaryPage(Personal_diary.this);
                    String pageName = txtInsertTitlePageDiary.getText().toString().trim();
                    int year = Integer.parseInt(timeController.getCurrentYear());
                    String timeStamp = timeController.timeStamp();
                    String history = txtInsertHistoryPageDiary.getText().toString();

                    Long id = dbPersonalDiaryPage.insertPageDiary(pageName,year,timeStamp,history);

                    if(id>0){
                        Toast.makeText(Personal_diary.this, "Save page", Toast.LENGTH_LONG).show();
                        txtInsertTitlePageDiary.setText("");
                    }else{
                        Toast.makeText(Personal_diary.this, "NOT save", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        loadDiaryPage = findViewById(R.id.loadDiaryPage);
        loadDiaryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateText(txtInsertTitlePageDiary.getText().toString())){
                    DbPersonalDiaryPage dbPersonalDiaryPage = new DbPersonalDiaryPage(Personal_diary.this);
                    List<String> information = dbPersonalDiaryPage.getPagesDiary(txtInsertTitlePageDiary.getText().toString().trim());
                    Toast.makeText(Personal_diary.this, "Found: +"+information.size() , Toast.LENGTH_LONG).show();

                    txtInsertHistoryPageDiary.setText("");
                    String txt = "";
                    for(int i = 0; i< information.size(); i++){
                        txt = txt + information.get(i) + "\n\n";
                    }
                    oldInformation = txt;
                    txtInsertHistoryPageDiary.setText(txt);
                }
            }
        });
    }


    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }

    public void saveOnlyNewInformation(String txt){

    }
}