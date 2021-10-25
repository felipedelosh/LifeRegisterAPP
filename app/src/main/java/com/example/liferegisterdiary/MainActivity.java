package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public Controller controller;
    //Capture a main screem items
    //Buttons
    private ImageButton btn_diary;
    private ImageButton btn_calendar;
    private ImageButton btn_drug_diary;
    private ImageButton btn_economy;
    private ImageButton btn_time_dist;
    private ImageButton btn_feelings;
    private ImageButton btn_graphics;
    private ImageButton btn_settings;
    private ImageButton btn_help;
    //End to cacth buttons

    //text
    private TextView main_output_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        controller = new Controller(context);

        //Cacth screem elements
        main_output_message = findViewById(R.id.main_output_message);
        showOutputMesagge(controller.health());

        setUpView();
    }

    //Configure a button click
    private void setUpView(){
        //Cacth buttons
        btn_diary = findViewById(R.id.btn_diary);
        btn_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDiaryView = new Intent(getApplicationContext(), Diary.class);
                startActivity(launchDiaryView);
            }
        });
        btn_calendar = findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_drug_diary = findViewById(R.id.btn_drug_diary);
        btn_drug_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDrugDiaryView = new Intent(getApplicationContext(), Drug_Diary.class);
                startActivity(launchDrugDiaryView);
            }
        });
        btn_economy = findViewById(R.id.btn_economy);
        btn_economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchEconomyView = new Intent(getApplicationContext(), Economy.class);
                startActivity(launchEconomyView);

            }
        });
        btn_time_dist = findViewById(R.id.btn_time_dist);
        btn_time_dist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchTimeDistView = new Intent(getApplicationContext(), TimeDistribution.class);
                startActivity(launchTimeDistView);
            }
        });
        btn_feelings = findViewById(R.id.btn_feelings);
        btn_feelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchFeelingsView = new Intent(getApplicationContext(), Feelings.class);
                startActivity(launchFeelingsView);
            }
        });
        btn_graphics = findViewById(R.id.btn_graphics);
        btn_graphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchGraphicsView = new Intent(getApplicationContext(), Graphics.class);
                startActivity(launchGraphicsView);
            }
        });
        btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSettingsView = new Intent(getApplicationContext(), Settings.class);
                startActivity(launchSettingsView);
            }
        });
        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void showOutputMesagge(String txt){
        main_output_message.setText(txt);
    }


}