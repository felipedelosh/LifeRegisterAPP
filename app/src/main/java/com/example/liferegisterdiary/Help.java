package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {

    private Button btnHelpUserManual;
    private Button btnHelpPosibilities;
    private Button btnHelpPrograming;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setupView();
    }

    private void setupView() {

        btnHelpUserManual = findViewById(R.id.btnHelpUserManual);
        btnHelpUserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnHelpPosibilities = findViewById(R.id.btnHelpPosibilities);
        btnHelpPosibilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchHelpPosibilitiesView = new Intent(getApplicationContext(), HelpListOfPosibilities.class);
                startActivity(launchHelpPosibilitiesView);
            }
        });
        btnHelpPrograming = findViewById(R.id.btnHelpPrograming);
        btnHelpPrograming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}