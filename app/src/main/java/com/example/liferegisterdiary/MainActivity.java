package com.example.liferegisterdiary;

/***
 * This is a main
 *
 * Contain an agent
 *
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import db.DbNotifications;

public class MainActivity extends AppCompatActivity {

    public Controller controller;
    //text
    private TextView mainOutputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Capture a contex
        Context context = this;
        //Capture a imagen background
        ImageView imgBG = findViewById(R.id.mainMenuImgBG);
        controller = new Controller(context, imgBG);

        //Cacth screem elements
        mainOutputMessage = findViewById(R.id.mainOutputMessage);
        showOutputMesagge(controller.getOutPutMessage());

        //User is register?
        if(!controller.userIsRegister()){
            Intent launchResgisterView = new Intent(getApplicationContext(), Register.class);
            startActivity(launchResgisterView);
        }else{
            startAgent();
        }

        setUpView();
    }

    //Configure a button click
    private void setUpView(){
        //Cacth buttons
        ImageButton btnDiary = findViewById(R.id.btnDiary);
        btnDiary.setOnClickListener(v -> {
            Intent launchDiaryView = new Intent(getApplicationContext(), Diary.class);
            startActivity(launchDiaryView);
        });
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(v -> {
            Intent launchCalendaryView = new Intent(getApplicationContext(), Calendary.class);
            startActivity(launchCalendaryView);
        });
        ImageButton btnDrugDiary = findViewById(R.id.btnDrugDiary);
        btnDrugDiary.setOnClickListener(v -> {
            Intent launchDrugDiaryView = new Intent(getApplicationContext(), DrugDiary.class);
            startActivity(launchDrugDiaryView);
        });
        ImageButton btnEconomy = findViewById(R.id.btnEconomy);
        btnEconomy.setOnClickListener(v -> {
            Intent launchEconomyView = new Intent(getApplicationContext(), Economy.class);
            startActivity(launchEconomyView);

        });
        ImageButton btnTimeDist = findViewById(R.id.btnTimeDist);
        btnTimeDist.setOnClickListener(v -> {
            Intent launchTimeDistView = new Intent(getApplicationContext(), TimeDistribution.class);
            startActivity(launchTimeDistView);
        });
        ImageButton btnFeelings = findViewById(R.id.btnFeelings);
        btnFeelings.setOnClickListener(v -> {
            Intent launchFeelingsView = new Intent(getApplicationContext(), Feelings.class);
            startActivity(launchFeelingsView);
        });
        ImageButton btnGraphics = findViewById(R.id.btnGraphics);
        btnGraphics.setOnClickListener(v -> {
            Intent launchGraphicsView = new Intent(getApplicationContext(), Graphics.class);
            startActivity(launchGraphicsView);
        });
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            Intent launchSettingsView = new Intent(getApplicationContext(), Settings.class);
            startActivity(launchSettingsView);
        });
        ImageButton btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(v -> {
            Intent launchHelpView = new Intent(getApplicationContext(), Help.class);
            startActivity(launchHelpView);
        });

    }

    public void showOutputMesagge(String txt){
        mainOutputMessage.setText(txt);
    }


    //Agent
    public void startAgent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    showOutputMesagge(controller.verifyLaunchers());
                    //Wait
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        //Do nothing
                    }
                }
            }
        }).start();
    }


}