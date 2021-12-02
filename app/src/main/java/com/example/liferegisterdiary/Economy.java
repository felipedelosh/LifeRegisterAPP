package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Economy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);

        setUpView();
    }

    //Events to buttons
    private void setUpView() {
        ImageButton btnEconomyInOut = findViewById(R.id.btnEconomyInOut);
        btnEconomyInOut.setOnClickListener(v -> {
            Intent launchTAccountsView = new Intent(getApplicationContext(), TAccounts.class);
            startActivity(launchTAccountsView);
        });
        ImageButton btnEconomyBox = findViewById(R.id.btnEconomyBox);
        btnEconomyBox.setOnClickListener(v -> {
            Intent launchBoxView = new Intent(getApplicationContext(), BoxEconomy.class);
            startActivity(launchBoxView);
        });
    }
}