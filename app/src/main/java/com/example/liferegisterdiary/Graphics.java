package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Graphics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        setView();
    }

    private void setView() {

        ImageButton btnGraphicTAccoutns = findViewById(R.id.btnGraphicTAccoutns);
        btnGraphicTAccoutns.setOnClickListener(v -> {
            Intent launchEconomyGraphicsView = new Intent(getApplicationContext(), EconomyGraphics.class);
            startActivity(launchEconomyGraphicsView);
        });
        ImageButton btnGraphicBox = findViewById(R.id.btnGraphicBox);
        btnGraphicBox.setOnClickListener(v -> {
            Intent launchEconomyGraphicsBoxEconomyView = new Intent(getApplicationContext(), BoxEconomyGraphics.class);
            startActivity(launchEconomyGraphicsBoxEconomyView);
        });
        ImageButton btnGraphicFeelings = findViewById(R.id.btnGraphicFeelings);
        btnGraphicFeelings.setOnClickListener(v -> {
            Intent launchFeelingsGraphicsView = new Intent(getApplicationContext(), FeelingsGraphics.class);
            startActivity(launchFeelingsGraphicsView);
        });
        ImageButton btnGraphicTimeInversion = findViewById(R.id.btnGraphicTimeInversion);
        btnGraphicTimeInversion.setOnClickListener(v -> {
            Intent launchTimeDistributionView = new Intent(getApplicationContext(), TimeDistributionGraphics.class);
            startActivity(launchTimeDistributionView);
        });
        ImageButton btnGraphicTimeline = findViewById(R.id.btnGraphicTimeline);
        btnGraphicTimeline.setOnClickListener(v -> {

        });
    }
}