package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import models.User;
import db.DbUser;

public class ProfileConfigure extends AppCompatActivity {

    private EditText txtUsername;
    private Spinner spinnerDayToBirthDate;
    private Spinner spinnerMonthToBirthDate;
    private Spinner spinnerYearToBirthDate;
    private Spinner spinnerSex;
    private TextView lblAge;
    private TextView txtUserDescription;
    private DbUser dbUser;
    private TimeController timeController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_configure);

        txtUsername = findViewById(R.id.txtUsername);
        spinnerDayToBirthDate = findViewById(R.id.spinnerDayToBirthDate);
        spinnerMonthToBirthDate = findViewById(R.id.spinnerMonthToBirthDate);
        spinnerYearToBirthDate = findViewById(R.id.spinnerYearToBirthDate);
        spinnerSex = findViewById(R.id.spinnerSex);
        txtUserDescription = findViewById(R.id.txtUserDescription);
        lblAge = findViewById(R.id.lblAge);


        dbUser = new DbUser(ProfileConfigure.this);
        timeController = new TimeController();

        putSpinnerOptions();

        if(dbUser.userIsregisted()){
            chargeDataInDisplay();
        }


        setUpView();
    }

    private void setUpView() {
        Button btnNotSaveProfileSettings = findViewById(R.id.btnNotSaveProfileSettings);
        btnNotSaveProfileSettings.setOnClickListener(v -> {
            Intent launchSettingsView = new Intent(getApplicationContext(), Settings.class);
            startActivity(launchSettingsView);
        });
        Button btnSaveProfileSettings = findViewById(R.id.btnSaveProfileSettings);
        btnSaveProfileSettings.setOnClickListener(v -> {

            String usrnm = txtUsername.getText().toString();

            if(validateText(usrnm)){

                String sex  = "";

                if(spinnerSex.getSelectedItemPosition() == 0){
                    sex = "male";
                }else{
                    sex = "female";
                }

                int yearBirthDate = Integer.parseInt(spinnerYearToBirthDate.getSelectedItem().toString().trim());
                int monthBirthDate = spinnerMonthToBirthDate.getSelectedItemPosition();
                int dayBirthDate = Integer.parseInt(spinnerDayToBirthDate.getSelectedItem().toString().trim());

                Long id = dbUser.editUser(usrnm, sex, yearBirthDate, monthBirthDate, dayBirthDate);

                if(id > 0){
                    chargeDataInDisplay();
                    Toast.makeText(ProfileConfigure.this, "Update User" , Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ProfileConfigure.this, "Dont'save" , Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    public void putSpinnerOptions(){

        //Put sex
        String [] genders = {getString(R.string.app_gender_male).trim(), getString(R.string.app_gender_female).trim()};
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, genders);
        spinnerSex.setAdapter(adapterSex);

        //Spinner year
        int [] k = timeController.getCommonYears();

        String [] spinerOptionsYear = new String[100];
        for(int i=0;i<k.length;i++){
            spinerOptionsYear[i] = String.valueOf(k[i]);
        }

        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptionsYear);
        //Put all values
        spinnerYearToBirthDate.setAdapter(adapterYear);

        //Put months
        String [] spinerOptionsDays = timeController.getMonths();
        ArrayAdapter <String> adapterDays = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinerOptionsDays);
        spinnerMonthToBirthDate.setAdapter(adapterDays);

        //Put days in spinner
        putDaysInSpinner();


    }


    public void putDaysInSpinner(){
        int k = timeController.getNumberDaysOfMounthX(spinnerMonthToBirthDate.getSelectedItem().toString().trim());
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
        spinnerDayToBirthDate.setAdapter(adapter);
    }

    public void chargeDataInDisplay(){
        User user = dbUser.getUser();

        txtUsername.setText(user.getUsername());
        //Put year in spinner
        if(user.getSex().equals("male")){
            spinnerSex.setSelection(0);
        }else{
            spinnerSex.setSelection(1);
        }
        int age = timeController.howManyYearsHavPassed(user.getDayBirthDate(), user.getMountBirthDate(), user.getYearBirthDate());
        lblAge.setText(Integer.toString(age));
        txtUserDescription.setText(user.toString());

        if(age<100){
            spinnerYearToBirthDate.setSelection(age);
        }
        spinnerMonthToBirthDate.setSelection(user.getMountBirthDate());
        spinnerDayToBirthDate.setSelection(user.getDayBirthDate()-1);

    }

    //Valitate if the text is rigth
    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }


}