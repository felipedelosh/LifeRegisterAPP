package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Diary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);


        setUpView();
    }


    private void setUpView(){

        ImageButton btnPersonalDiary = findViewById(R.id.btnPersonalDiary);
        btnPersonalDiary.setOnClickListener(v -> {
            Intent launchPersonalDiaryView = new Intent(getApplicationContext(), Personaldiary.class);
            startActivity(launchPersonalDiaryView);
        });
        ImageButton btnDreamDiary = findViewById(R.id.btnDreamDiary);
        btnDreamDiary.setOnClickListener(v -> {
            Intent launchDreamDiaryView = new Intent(getApplicationContext(), DreamDiary.class);
            startActivity(launchDreamDiaryView);
        });
        ImageButton btnGratirudeDiary = findViewById(R.id.btnGratitudeDiary);
        btnGratirudeDiary.setOnClickListener(v -> {
            Intent launchGratitudeDiaryView = new Intent(getApplicationContext(), GratirudeDiary.class);
            startActivity(launchGratitudeDiaryView);
        });
        ImageButton btnPsicotropeDiary = findViewById(R.id.btnPsicotropeDiary);
        btnPsicotropeDiary.setOnClickListener(v -> {
            Intent launchDrugDiaryView = new Intent(getApplicationContext(), DrugDiaryCounter.class);
            startActivity(launchDrugDiaryView);
        });
    }
}