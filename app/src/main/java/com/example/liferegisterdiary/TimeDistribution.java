package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.TimeDistributionAdapter;
import models.ItemTimeInversion;
import db.DbTimeDistribution;

public class TimeDistribution extends AppCompatActivity {

    private TimeController timeController;

    private TextView timeInversionHourItem6am;

    private Spinner spinnerTimeInversionSpinnerActivity6am;
    private Spinner spinnerTimeInversionSpinnerActivity7am;
    private Spinner spinnerTimeInversionSpinnerActivity8am;
    private Spinner spinnerTimeInversionSpinnerActivity9am;
    private Spinner spinnerTimeInversionSpinnerActivity10am;
    private Spinner spinnerTimeInversionSpinnerActivity11am;
    private Spinner spinnerTimeInversionSpinnerActivity12am;
    private Spinner spinnerTimeInversionSpinnerActivity1pm;
    private Spinner spinnerTimeInversionSpinnerActivity2pm;
    private Spinner spinnerTimeInversionSpinnerActivity3pm;
    private Spinner spinnerTimeInversionSpinnerActivity4pm;
    private Spinner spinnerTimeInversionSpinnerActivity5pm;
    private Spinner spinnerTimeInversionSpinnerActivity6pm;
    private Spinner spinnerTimeInversionSpinnerActivity7pm;
    private Spinner spinnerTimeInversionSpinnerActivity8pm;
    private Spinner spinnerTimeInversionSpinnerActivity9pm;
    private Spinner spinnerTimeInversionSpinnerActivity10pm;
    private Spinner spinnerTimeInversionSpinnerActivity11pm;
    private Spinner spinnerTimeInversionSpinnerActivity12pm;
    private Spinner spinnerTimeInversionSpinnerActivity1am;
    private Spinner spinnerTimeInversionSpinnerActivity2am;
    private Spinner spinnerTimeInversionSpinnerActivity3am;
    private Spinner spinnerTimeInversionSpinnerActivity4am;
    private Spinner spinnerTimeInversionSpinnerActivity5am;

    private ArrayList<Spinner> snprTimeInverSion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_distribution);

        timeController = new TimeController();

        Context context = this;

        snprTimeInverSion = new ArrayList<>();

        initViewElements();
        setAllSpinerOptions();
        setUpView();
    }

    private void initViewElements() {
        spinnerTimeInversionSpinnerActivity6am = findViewById(R.id.spinnerTimeInversionSpinnerActivity6am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity6am);
        spinnerTimeInversionSpinnerActivity7am = findViewById(R.id.spinnerTimeInversionSpinnerActivity7am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity7am);
        spinnerTimeInversionSpinnerActivity8am = findViewById(R.id.spinnerTimeInversionSpinnerActivity8am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity8am);
        spinnerTimeInversionSpinnerActivity9am = findViewById(R.id.spinnerTimeInversionSpinnerActivity9am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity9am);
        spinnerTimeInversionSpinnerActivity10am = findViewById(R.id.spinnerTimeInversionSpinnerActivity10am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity10am);
        spinnerTimeInversionSpinnerActivity11am = findViewById(R.id.spinnerTimeInversionSpinnerActivity11am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity11am);
        spinnerTimeInversionSpinnerActivity12am = findViewById(R.id.spinnerTimeInversionSpinnerActivity12am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity12am);
        spinnerTimeInversionSpinnerActivity1pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity1pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity1pm);
        spinnerTimeInversionSpinnerActivity2pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity2pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity2pm);
        spinnerTimeInversionSpinnerActivity3pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity3pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity3pm);
        spinnerTimeInversionSpinnerActivity4pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity4pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity4pm);
        spinnerTimeInversionSpinnerActivity5pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity5pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity5pm);
        spinnerTimeInversionSpinnerActivity6pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity6pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity6pm);
        spinnerTimeInversionSpinnerActivity7pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity7pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity7pm);
        spinnerTimeInversionSpinnerActivity8pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity8pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity8pm);
        spinnerTimeInversionSpinnerActivity9pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity9pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity9pm);
        spinnerTimeInversionSpinnerActivity10pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity10pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity10pm);
        spinnerTimeInversionSpinnerActivity11pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity11pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity11pm);
        spinnerTimeInversionSpinnerActivity12pm = findViewById(R.id.spinnerTimeInversionSpinnerActivity12pm);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity12pm);
        spinnerTimeInversionSpinnerActivity1am = findViewById(R.id.spinnerTimeInversionSpinnerActivity1am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity1am);
        spinnerTimeInversionSpinnerActivity2am = findViewById(R.id.spinnerTimeInversionSpinnerActivity2am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity2am);
        spinnerTimeInversionSpinnerActivity3am = findViewById(R.id.spinnerTimeInversionSpinnerActivity3am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity3am);
        spinnerTimeInversionSpinnerActivity4am = findViewById(R.id.spinnerTimeInversionSpinnerActivity4am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity4am);
        spinnerTimeInversionSpinnerActivity5am = findViewById(R.id.spinnerTimeInversionSpinnerActivity5am);
        snprTimeInverSion.add(spinnerTimeInversionSpinnerActivity5am);
    }


    private void setUpView(){

        Button saveDiaryActivities = findViewById(R.id.saveDiaryActivities);
        saveDiaryActivities.setOnClickListener(v -> {

        });


    }

    public ArrayList<String> getSpinerActivities(){

        ArrayList<String> spinerOptions = new ArrayList<>();

        try {

            DbTimeDistribution dbTimeDistribution = new DbTimeDistribution(TimeDistribution.this);
            List<String> dataControllerActivities = dbTimeDistribution.getActivitiesList();

            for(int i=0;i<dataControllerActivities.size();i++){
                spinerOptions.add(dataControllerActivities.get(i));
            }

        }catch (Exception e){
            //Do nothing
        }

        return spinerOptions;
    }


    /**
     * Enter a integer (0, 23)
     * And convert this a 6 am  ... 12 pm... 5 am hour
     * return a String with 12h
     * */
    private String getHour(int iterator){

        String formatAMPM = "am";
        int hour12h = 6;

        if((hour12h + iterator) < 13){
            hour12h = hour12h + iterator;
        }else{
            hour12h = iterator - 6;
            formatAMPM = "pm";

            if ((iterator - 6)>12){
                hour12h = iterator - 18;
                formatAMPM = "am";
            }
        }

        return hour12h+formatAMPM;
    }

    public void setAllSpinerOptions(){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getSpinerActivities());

        for(int i=0;i<snprTimeInverSion.size();i++){
            snprTimeInverSion.get(i).setAdapter(adapter);
        }

    }
}