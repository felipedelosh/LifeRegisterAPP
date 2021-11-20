package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    private Button btnSettingsProfile;
    private Button btnSettingsActivities;
    private Button btnSettingsFeelings;
    private Button btnSettingsDrugs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setUpView();
    }


    private void setUpView(){
        btnSettingsProfile = findViewById(R.id.btnSettingsProfile);
        btnSettingsProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchProfileConfigView = new Intent(getApplicationContext(), ProfileConfigure.class);
                startActivity(launchProfileConfigView);
            }
        });
        btnSettingsActivities = findViewById(R.id.btnSettingsActivities);
        btnSettingsActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivitiesConfigView = new Intent(getApplicationContext(), ActivitiesConfigure.class);
                startActivity(launchActivitiesConfigView);
            }
        });
        btnSettingsFeelings = findViewById(R.id.btnSettingsFeelings);
        btnSettingsFeelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchFeelingsConfigView = new Intent(getApplicationContext(), FeelingsConfigure.class);
                startActivity(launchFeelingsConfigView);
            }
        });
        btnSettingsDrugs = findViewById(R.id.btnSettingsDrugs);
        btnSettingsDrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDrugsConfigView = new Intent(getApplicationContext(), DrugsConfigure.class);
                startActivity(launchDrugsConfigView);
            }
        });
    }
}