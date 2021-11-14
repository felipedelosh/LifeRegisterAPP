package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DbGratitudeHistory;

public class Gratirude_Diary extends AppCompatActivity {

    private EditText txtGratitudeHistory;
    private Button saveGratitudePage;

    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratirude_diary);

        timeController = new TimeController();

        txtGratitudeHistory = findViewById(R.id.txtGratitudeHistory);


        setUpView();
    }

    private void setUpView() {
        saveGratitudePage = findViewById(R.id.saveGratitudePage);
        saveGratitudePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateText(txtGratitudeHistory.getText().toString())) {
                    DbGratitudeHistory dbGratitudeHistory = new DbGratitudeHistory(Gratirude_Diary.this);
                    String history = txtGratitudeHistory.getText().toString();
                    String timeStamp = timeController.timeStamp();

                    Long id = dbGratitudeHistory.insertGratirudeHistory(history, timeStamp);

                    if(id>0){
                        Toast.makeText(Gratirude_Diary.this, "Save page", Toast.LENGTH_LONG).show();
                        txtGratitudeHistory.setText("");
                    }else{
                        Toast.makeText(Gratirude_Diary.this, "NOT save", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }
}