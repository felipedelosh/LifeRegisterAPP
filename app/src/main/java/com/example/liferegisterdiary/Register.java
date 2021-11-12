package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.Serializable;

public class Register extends AppCompatActivity{

    private Button btnRegister;
    private Button btnCreateNickName;
    private Button btnSuccesfullRegister;
    private ScrollView scrollViewregister;
    private ImageButton sexMale;
    private ImageButton sexFemale;
    private LinearLayout registerPage0;
    private LinearLayout registerPage1;
    private LinearLayout registerPage2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get a main controller


        //Cacth display elemetns
        registerPage0 = findViewById(R.id.registerPage0);
        registerPage1 = findViewById(R.id.registerPage1);
        registerPage2 = findViewById(R.id.registerPage2);
        scrollViewregister = findViewById(R.id.scrollViewregister);

        //Hide page 1 and 2
        showViewRegisterInterface(0);

        setUpView();
    }

    private void setUpView(){
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewRegisterInterface(1);
            }
        });
        btnCreateNickName = findViewById(R.id.btnCreateNickName);
        btnCreateNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewRegisterInterface(2);
            }
        });
        sexMale = findViewById(R.id.sexMale);
        sexMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sexFemale = findViewById(R.id.sexFemale);
        sexFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSuccesfullRegister = findViewById(R.id.btnSuccesfullRegister);
        btnSuccesfullRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showViewRegisterInterface(int numberRegisterInterface){
        if(numberRegisterInterface == 0){
            registerPage0.setVisibility(View.VISIBLE);
            registerPage1.setVisibility(View.INVISIBLE);
            registerPage2.setVisibility(View.INVISIBLE);
        }
        if(numberRegisterInterface == 1){
            registerPage0.setVisibility(View.INVISIBLE);
            registerPage1.setVisibility(View.VISIBLE);
            registerPage2.setVisibility(View.INVISIBLE);
            scrollViewregister.post(new Runnable() {
                @Override
                public void run() {
                    scrollViewregister.scrollTo(0, registerPage0.getBottom());
                }
            });
        }
        if(numberRegisterInterface == 2){
            registerPage0.setVisibility(View.INVISIBLE);
            registerPage1.setVisibility(View.VISIBLE);
            registerPage2.setVisibility(View.VISIBLE);
            scrollViewregister.post(new Runnable() {
                @Override
                public void run() {
                    scrollViewregister.scrollTo(0, registerPage1.getBottom());
                }
            });
        }
    }

}