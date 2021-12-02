package com.example.liferegisterdiary;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import db.DbUser;

public class Register extends AppCompatActivity{

    private ScrollView scrollViewregister;
    private ImageView sexAvatar;
    private LinearLayout registerPage0;
    private LinearLayout registerPage1;
    private LinearLayout registerPage2;
    private Spinner spinnerRegisterDays;
    private Spinner spinnerRegisterMouhtns;
    private Spinner spinnerRegisterYear;


    private EditText txtUsername;
    private boolean sexSelected;

    //To create a user
    private String username;
    private String sex;

    TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //Controllers
        timeController = new TimeController();


        sexAvatar = findViewById(R.id.sex_avatar);
        spinnerRegisterYear = findViewById(R.id.spinnerRegisterYear);
        putYearsInSpinner(spinnerRegisterYear);
        spinnerRegisterMouhtns = findViewById(R.id.spinnerRegisterMouhtns);
        putMounthsInSpinner(spinnerRegisterMouhtns);
        spinnerRegisterDays = findViewById(R.id.spinnerRegisterDays);
        putDaysInSpinner(spinnerRegisterDays);
        spinnerInDefaultDate(spinnerRegisterYear,spinnerRegisterMouhtns,spinnerRegisterDays);

        //If the user update spiner months the days change

        //vars control
        sexSelected = false;

        //Cacth display elemetns
        registerPage0 = findViewById(R.id.registerPage0);
        registerPage1 = findViewById(R.id.registerPage1);
        registerPage2 = findViewById(R.id.registerPage2);
        scrollViewregister = findViewById(R.id.scrollViewregister);
        txtUsername = findViewById(R.id.txtUsername);

        //Hide page 1 and 2
        showViewRegisterInterface(0);

        setUpView();
    }

    private void setUpView(){
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            //Continue with register interface
            showViewRegisterInterface(1);
        });
        Button btnCreateNickName = findViewById(R.id.btnCreateNickName);
        btnCreateNickName.setOnClickListener(v -> {
            if(validateText(txtUsername.getText().toString())){
                txtUsername.setBackgroundColor(Color.GREEN);
                username = txtUsername.getText().toString().trim();
                showViewRegisterInterface(2);
            }else{
                txtUsername.setBackgroundColor(Color.RED);
            }
        });
        ImageButton sexMale = findViewById(R.id.sexMale);
        sexMale.setOnClickListener(v -> {
            sexSelected = true;
            sex = "male";
            sexAvatar.setImageResource(R.drawable.avatar_man);

        });

        ImageButton sexFemale = findViewById(R.id.sexFemale);
        sexFemale.setOnClickListener(v -> {
            sexSelected = true;
            sex = "female";
            sexAvatar.setImageResource(R.drawable.avatar_woman);
        });

        Button btnSuccesfullRegister = findViewById(R.id.btnSuccesfullRegister);
        btnSuccesfullRegister.setOnClickListener(v -> {

            if (sexSelected){

                try{

                    DbUser user = new DbUser(Register.this);
                    int yearBirthDate = Integer.parseInt(spinnerRegisterYear.getSelectedItem().toString().trim());
                    int mountBirthDate = timeController.getNomberOfMountX(spinnerRegisterMouhtns.getSelectedItem().toString().trim());
                    int dayBirthDate = Integer.parseInt(spinnerRegisterDays.getSelectedItem().toString().trim());
                    Long id = user.insertUser(username, sex, yearBirthDate, mountBirthDate, dayBirthDate);

                    if(id > 0){
                        Toast.makeText(Register.this, "User save", Toast.LENGTH_LONG).show();
                        Intent launchMainView = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(launchMainView);
                    }else{
                        Toast.makeText(Register.this, "User NOT save", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    //Do nothing
                }

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
            scrollViewregister.post(() -> scrollViewregister.scrollTo(0, registerPage0.getBottom()));
        }
        if(numberRegisterInterface == 2){
            registerPage0.setVisibility(View.INVISIBLE);
            registerPage1.setVisibility(View.VISIBLE);
            registerPage2.setVisibility(View.VISIBLE);
            scrollViewregister.post(() -> scrollViewregister.scrollTo(0, registerPage1.getBottom()));
        }
    }

    //Valitate if the text is rigth
    public boolean validateText(String txt){
    return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }

    public void putDaysInSpinner(Spinner spinnerRegisterDays) {

        int k = timeController.getNumberDaysOfMounthX(spinnerRegisterMouhtns.getSelectedItem().toString().trim());
        String [] spinerOptions = null;
        //Case 28
        if(k == 28){
            spinerOptions = new String[28];
            for(int i=0;i<28;i++){
                spinerOptions[i] = String.valueOf(i+1);
            }
        }

        //Case 30
        if(k == 30){
            spinerOptions = new String[30];
            for(int i=0;i<30;i++){
                spinerOptions[i] = String.valueOf(i+1);
            }
        }

        //Case 31
        if(k == 31){
            spinerOptions = new String[31];
            for(int i=0;i<31;i++){
                spinerOptions[i] = String.valueOf(i+1);
            }
        }

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerRegisterDays.setAdapter(adapter);

    }

    public void putMounthsInSpinner(Spinner spinnerRegisterMouhtns){
        String [] spinerOptions = timeController.getMonths();
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerRegisterMouhtns.setAdapter(adapter);
    }

    public void putYearsInSpinner(Spinner spinnerRegisterYear){

        int [] k = timeController.getCommonYears();

        String [] spinerOptions = new String[100];
        for(int i=0;i<k.length;i++){
            spinerOptions[i] = String.valueOf(k[i]);
        }

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptions);
        spinnerRegisterYear.setAdapter(adapter);
    }

    public void spinnerInDefaultDate(Spinner spinnerRegisterYear, Spinner spinnerRegisterMouhtns, Spinner spinnerRegisterDays){
        spinnerRegisterDays.setSelection(12);
        spinnerRegisterMouhtns.setSelection(1);

        int k = Integer.parseInt(timeController.getCurrentYear());
        if(k>99){
            spinnerRegisterYear.setSelection(k - 1991);
        }else{
            spinnerRegisterYear.setSelection(10);
        }
    }


}