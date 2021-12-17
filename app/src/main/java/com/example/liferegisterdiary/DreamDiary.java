package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import db.DbDreamDiaryPage;

public class DreamDiary extends AppCompatActivity {

    private EditText txtInsertTitleDreamDiary;
    private EditText txtInsertHistoryDreamDiary;

    TimeController timeController;

    //Control not insert duplicated information
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

        Button btnSaveDream = findViewById(R.id.btnSaveDream);
        btnSaveDream.setOnClickListener(v -> {
            if(validateText(txtInsertTitleDreamDiary.getText().toString()) && validateText(txtInsertHistoryDreamDiary.getText().toString())){
                DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(DreamDiary.this);
                String dreamName = txtInsertTitleDreamDiary.getText().toString();
                int year = Integer.parseInt(timeController.getCurrentYear());
                String timeStamp = timeController.timeStamp();
                String history = txtInsertHistoryDreamDiary.getText().toString();

                long id = dbDreamDiaryPage.insertDreamDiary(dreamName, year, timeStamp, prepareToSQL(history));

                if(id>0){
                    Toast.makeText(DreamDiary.this, "Save page", Toast.LENGTH_LONG).show();
                    txtInsertTitleDreamDiary.setText("");
                    txtInsertHistoryDreamDiary.setText("");
                }else{
                    Toast.makeText(DreamDiary.this, "NOT save", Toast.LENGTH_LONG).show();
                }

            }

        });
        Button btnLoadDream = findViewById(R.id.btnLoadDream);
        btnLoadDream.setOnClickListener(v -> {

            if(validateText(txtInsertTitleDreamDiary.getText().toString())){

                DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(DreamDiary.this);
                List<String> information = dbDreamDiaryPage.getPagesDreamDiary(txtInsertTitleDreamDiary.getText().toString());
                Toast.makeText(DreamDiary.this, "Found: +"+information.size() , Toast.LENGTH_LONG).show();

                txtInsertHistoryDreamDiary.setText("");
                String txt = "";
                for(int i = 0; i< information.size(); i++){
                    txt = txt + showFromSQLText(information.get(i)) + "\n\n";
                }
                oldInformation = txt;
                txtInsertHistoryDreamDiary.setText(txt);

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