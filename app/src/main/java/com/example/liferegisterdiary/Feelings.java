package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Feelings extends AppCompatActivity {

    private Spinner spinnerFeelings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);

        //Catch spinner and set options
        spinnerFeelings = findViewById(R.id.spinnerFeelings);
        ArrayList<String> spinerOptions = getSpinerFeeelings();
        ArrayAdapter<String> sprAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerFeelings.setAdapter(sprAdapter);
    }

    public ArrayList<String> getSpinerFeeelings(){
        String [] dataControllerActivities = {"Sentimiento a", "Sentimiento b", "Sentimiento c", "Sentimiento d"};

        ArrayList<String> spinerOptions = new ArrayList<>();

        for(int i=0;i<dataControllerActivities.length;i++){
            spinerOptions.add(dataControllerActivities[i]);
        }

        return spinerOptions;
    }
}