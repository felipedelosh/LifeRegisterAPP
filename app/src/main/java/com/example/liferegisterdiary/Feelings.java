package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.DbFeeling;

public class Feelings extends AppCompatActivity {

    private Spinner spinnerFeelings;
    private Button btn_save_feeling;

    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);

        //Catch spinner and set options
        spinnerFeelings = findViewById(R.id.spinnerFeelings);
        ArrayList<String> spinerOptions = getSpinerFeeelings();
        ArrayAdapter<String> sprAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerFeelings.setAdapter(sprAdapter);

        timeController = new TimeController();

        setUpView();
    }


    private void setUpView(){

        btn_save_feeling = findViewById(R.id.btn_save_feeling);
        btn_save_feeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = timeController.timeStamp();
                String feelingName = spinnerFeelings.getSelectedItem().toString().trim();
                DbFeeling dbFeeling = new DbFeeling(Feelings.this);
                Long id = dbFeeling.insertFeelingCount(timeStamp, feelingName);

                if(id > 0){
                    Toast.makeText(Feelings.this, "Registed", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Feelings.this, "Dont'save", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public ArrayList<String> getSpinerFeeelings(){
        DbFeeling dbFeeling = new DbFeeling(Feelings.this);

        List<String> allFeelings = dbFeeling.getFeelingsList();



        ArrayList<String> spinerOptions = new ArrayList<>();

        for(int i=0;i<allFeelings.size();i++){
            spinerOptions.add(allFeelings.get(i));
        }

        return spinerOptions;
    }
}