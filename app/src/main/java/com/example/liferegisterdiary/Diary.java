package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Diary extends AppCompatActivity {

    private ImageButton btn_personal_diary;
    private ImageButton btn_dream_diary;
    private ImageButton btn_gratitude_diary;
    private ImageButton btn_psicotrope_diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);


        setUpView();
    }


    private void setUpView(){

        btn_personal_diary = findViewById(R.id.btn_personal_diary);
        btn_personal_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchPersonalDiaryView = new Intent(getApplicationContext(), Personal_diary.class);
                startActivity(launchPersonalDiaryView);
            }
        });
        btn_dream_diary = findViewById(R.id.btn_dream_diary);
        btn_dream_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDreamDiaryView = new Intent(getApplicationContext(), Dream_diary.class);
                startActivity(launchDreamDiaryView);
            }
        });
        btn_gratitude_diary = findViewById(R.id.btn_gratitude_diary);
        btn_gratitude_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_psicotrope_diary = findViewById(R.id.btn_psicotrope_diary);
        btn_psicotrope_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}