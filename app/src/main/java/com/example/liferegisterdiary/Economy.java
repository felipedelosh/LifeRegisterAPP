package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Economy extends AppCompatActivity {

    private ImageButton btn_economy_in_out;
    private ImageButton btn_economy_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);

        setUpView();
    }

    //Events to buttons
    private void setUpView() {
        btn_economy_in_out = findViewById(R.id.btn_economy_in_out);
        btn_economy_in_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_economy_box = findViewById(R.id.btn_economy_box);
        btn_economy_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}