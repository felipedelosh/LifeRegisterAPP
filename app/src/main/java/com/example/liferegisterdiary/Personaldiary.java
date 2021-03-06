package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.DbPersonalDiaryPage;


public class Personaldiary extends AppCompatActivity {

    private Button saveDiaryPage;
    private Button loadDiaryPage;
    private EditText txtInsertTitlePageDiary;
    private EditText txtInsertHistoryPageDiary;


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
        saveDiaryPage.setOnClickListener(v -> {

            if(validateText(txtInsertTitlePageDiary.getText().toString()) && validateText(txtInsertHistoryPageDiary.getText().toString())){


                try{
                    DbPersonalDiaryPage dbPersonalDiaryPage = new DbPersonalDiaryPage(Personaldiary.this);
                    String pageName = txtInsertTitlePageDiary.getText().toString().trim();
                    int year = Integer.parseInt(timeController.getCurrentYear());
                    String timeStamp = timeController.timeStamp();
                    String history = txtInsertHistoryPageDiary.getText().toString();

                    Long id = dbPersonalDiaryPage.insertPageDiary(pageName,year,timeStamp,prepareToSQL(history));

                    if(id>0){
                        Toast.makeText(Personaldiary.this, "Save page", Toast.LENGTH_LONG).show();
                        txtInsertTitlePageDiary.setText("");
                        txtInsertHistoryPageDiary.setText("");
                    }else{
                        Toast.makeText(Personaldiary.this, "NOT save", Toast.LENGTH_LONG).show();
                    }


                }catch (Exception e){
                    //Do nothing
                }
            }
        });

        loadDiaryPage = findViewById(R.id.loadDiaryPage);
        loadDiaryPage.setOnClickListener(v -> {

            if(validateText(txtInsertTitlePageDiary.getText().toString())){

                try{

                    DbPersonalDiaryPage dbPersonalDiaryPage = new DbPersonalDiaryPage(Personaldiary.this);
                    List<String> information = dbPersonalDiaryPage.getPagesDiary(txtInsertTitlePageDiary.getText().toString().trim());
                    Toast.makeText(Personaldiary.this, "Found: +"+information.size() , Toast.LENGTH_LONG).show();

                    txtInsertHistoryPageDiary.setText("");
                    String txt = "";
                    for(int i = 0; i< information.size(); i++){
                        txt = txt + showFromSQLText(information.get(i)) + "\n\n";
                    }

                    txtInsertHistoryPageDiary.setText(txt);

                }catch (Exception e){
                    //Do nothing
                }

            }
        });
    }


    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }

    /***
     * repair a \n \' \? caracters
     *
     * @return 'TEXTSIMPLE'
     */
    public String prepareToSQL(String txt){

        String output = "";

        String [] parts = txt.split("\n");

        for(int i=0;i<parts.length;i++){
            output = output + "\\n" + parts[i];
        }


        return output;
    }

    /****
     * SQL TEXT enter with \n \' \? caracters
     * i create bread lines outpu
     * @param txt
     * @return
     */
    public String showFromSQLText(String txt){
        return txt.replace("\\n", "\n");
    }
}