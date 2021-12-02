package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DbGratitudeHistory;

public class GratirudeDiary extends AppCompatActivity {

    private EditText txtGratitudeHistory;

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
        Button saveGratitudePage = findViewById(R.id.saveGratitudePage);
        saveGratitudePage.setOnClickListener(v -> {

            try{
                if(validateText(txtGratitudeHistory.getText().toString())) {
                    DbGratitudeHistory dbGratitudeHistory = new DbGratitudeHistory(GratirudeDiary.this);
                    String history = txtGratitudeHistory.getText().toString();
                    String timeStamp = timeController.timeStamp();

                    Long id = dbGratitudeHistory.insertGratirudeHistory(history, timeStamp);

                    if(id>0){
                        Toast.makeText(GratirudeDiary.this, "Save page", Toast.LENGTH_LONG).show();
                        txtGratitudeHistory.setText("");
                    }else{
                        Toast.makeText(GratirudeDiary.this, "NOT save", Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                Toast.makeText(GratirudeDiary.this, "Error :(", Toast.LENGTH_LONG).show();
            }



        });
    }

    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }
}