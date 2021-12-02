package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setUpView();
    }


    private void setUpView(){
        Button btnSettingsProfile = findViewById(R.id.btnSettingsProfile);
        btnSettingsProfile.setOnClickListener(v -> {
            Intent launchProfileConfigView = new Intent(getApplicationContext(), ProfileConfigure.class);
            startActivity(launchProfileConfigView);
        });
        Button btnSettingsActivities = findViewById(R.id.btnSettingsActivities);
        btnSettingsActivities.setOnClickListener(v -> {
            Intent launchActivitiesConfigView = new Intent(getApplicationContext(), ActivitiesConfigure.class);
            startActivity(launchActivitiesConfigView);
        });
        Button btnSettingsFeelings = findViewById(R.id.btnSettingsFeelings);
        btnSettingsFeelings.setOnClickListener(v -> {
            Intent launchFeelingsConfigView = new Intent(getApplicationContext(), FeelingsConfigure.class);
            startActivity(launchFeelingsConfigView);
        });
        Button btnSettingsDrugs = findViewById(R.id.btnSettingsDrugs);
        btnSettingsDrugs.setOnClickListener(v -> {
            Intent launchDrugsConfigView = new Intent(getApplicationContext(), DrugsConfigure.class);
            startActivity(launchDrugsConfigView);
        });

        Button btnSettingsPassword = findViewById(R.id.btnSettingsPassword);
        btnSettingsPassword.setOnClickListener(v -> {
            Intent launchPasswordView = new Intent(getApplicationContext(), Password.class);
            startActivity(launchPasswordView);
        });




    }
}