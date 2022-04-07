package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import db.DbTAccount;

public class Economy extends AppCompatActivity {

    private DbTAccount dbTAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);
        dbTAccount = new DbTAccount(this);
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

        //render only exit so much TAccounts registers
        ImageButton btnSearchMoney = findViewById(R.id.btnSearchMoney);
        btnSearchMoney.setOnClickListener(v -> {
            Intent launchBoxView = new Intent(getApplicationContext(), SearchMoney.class);
            startActivity(launchBoxView);
        });
        if(dbTAccount.getTaccountReguistersCounter() < 99){
            btnSearchMoney.setVisibility(View.INVISIBLE);
        }

    }
}