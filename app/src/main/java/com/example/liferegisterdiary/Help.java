package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setupView();
    }

    private void setupView() {

        Button btnHelpUserManual = findViewById(R.id.btnHelpUserManual);
        btnHelpUserManual.setOnClickListener(v -> {

        });
        Button btnHelpPosibilities = findViewById(R.id.btnHelpPosibilities);
        btnHelpPosibilities.setOnClickListener(v -> {
            Intent launchHelpPosibilitiesView = new Intent(getApplicationContext(), HelpListOfPosibilities.class);
            startActivity(launchHelpPosibilitiesView);
        });
        Button btnHelpPrograming = findViewById(R.id.btnHelpPrograming);
        btnHelpPrograming.setOnClickListener(v -> {

        });
    }
}