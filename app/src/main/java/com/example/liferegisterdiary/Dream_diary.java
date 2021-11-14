package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import db.DbDreamDiaryPage;
import db.DbPersonalDiaryPage;

public class Dream_diary extends AppCompatActivity {

    private Button btnSaveDream;
    private Button btnLoadDream;
    private EditText txtInsertTitleDreamDiary;
    private EditText txtInsertHistoryDreamDiary;

    TimeController timeController;

    //Control not inser duplicated information
    String oldInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_diary);

        timeController = new TimeController();

        txtInsertTitleDreamDiary = findViewById(R.id.txtInsertTitleDreamDiary);
        txtInsertHistoryDreamDiary = findViewById(R.id.txtInsertHistoryDreamDiary);

        setUpView();
    }

    //Configure a button click
    private void setUpView(){

        btnSaveDream = findViewById(R.id.btnSaveDream);
        btnSaveDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateText(txtInsertTitleDreamDiary.getText().toString()) && validateText(txtInsertHistoryDreamDiary.getText().toString())){
                    DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(Dream_diary.this);
                    String dreamName = txtInsertTitleDreamDiary.getText().toString();
                    int year = Integer.parseInt(timeController.getCurrentYear());
                    String timeStamp = timeController.timeStamp();
                    String history = txtInsertHistoryDreamDiary.getText().toString();

                    long id = dbDreamDiaryPage.insertDreamDiary(dreamName, year, timeStamp, history);

                    if(id>0){
                        Toast.makeText(Dream_diary.this, "Save page", Toast.LENGTH_LONG).show();
                        txtInsertTitleDreamDiary.setText("");
                    }else{
                        Toast.makeText(Dream_diary.this, "NOT save", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
        btnLoadDream = findViewById(R.id.btnLoadDream);
        btnLoadDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateText(txtInsertTitleDreamDiary.getText().toString())){

                    DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(Dream_diary.this);
                    List<String> information = dbDreamDiaryPage.getPagesDreamDiary(txtInsertTitleDreamDiary.getText().toString());
                    Toast.makeText(Dream_diary.this, "Found: +"+information.size() , Toast.LENGTH_LONG).show();

                    txtInsertHistoryDreamDiary.setText("");
                    String txt = "";
                    for(int i = 0; i< information.size(); i++){
                        txt = txt + information.get(i) + "\n\n";
                    }
                    oldInformation = txt;
                    txtInsertHistoryDreamDiary.setText(txt);

                }

            }
        });
    }

    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }

}