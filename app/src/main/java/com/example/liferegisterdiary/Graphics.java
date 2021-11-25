package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Graphics extends AppCompatActivity {

    private ImageButton btn_graphic_taccoutns;
    private ImageButton btn_graphic_box;
    private ImageButton btn_graphic_feelings;
    private ImageButton btn_graphic_time_inversion;
    private ImageButton btn_graphic_timeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        setView();
    }

    private void setView() {

        btn_graphic_taccoutns = findViewById(R.id.btn_graphic_taccoutns);
        btn_graphic_taccoutns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchEconomyGraphicsView = new Intent(getApplicationContext(), EconomyGraphics.class);
                startActivity(launchEconomyGraphicsView);
            }
        });
        btn_graphic_box = findViewById(R.id.btn_graphic_box);
        btn_graphic_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_graphic_feelings = findViewById(R.id.btn_graphic_feelings);
        btn_graphic_feelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchFeelingsGraphicsView = new Intent(getApplicationContext(), FeelingsGraphics.class);
                startActivity(launchFeelingsGraphicsView);
            }
        });
        btn_graphic_time_inversion = findViewById(R.id.btn_graphic_time_inversion);
        btn_graphic_time_inversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_graphic_timeline = findViewById(R.id.btn_graphic_timeline);
        btn_graphic_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}