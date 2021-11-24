package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.TimeDistributionAdapter;
import Models.ItemTimeInversion;
import db.DbTimeDistribution;

public class TimeDistribution extends AppCompatActivity {


    private TimeDistributionAdapter timeDistributionAdapter;
    private ArrayList<ItemTimeInversion> arrayListItemTimeInversions;
    private Context context;
    private ListView listViewTimeInversion;

    private Button saveDiaryActivities;

    private TimeController timeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_distribution);

        timeController = new TimeController();

        context = this;

        //Get all Activities
        ArrayList<String> spinerOptions = getSpinerActivities();

        //Generate a time distribution items
        listViewTimeInversion = findViewById(R.id.listViewTimeInversion);
        arrayListItemTimeInversions = new ArrayList<>();
        timeDistributionAdapter = new TimeDistributionAdapter(arrayListItemTimeInversions, context, getSpinerActivities());
        listViewTimeInversion.setAdapter(timeDistributionAdapter);

        for(int i=0;i<24;i++){
            createNewItemTimeInversionAdapter(i);
        }

        setUpView();
    }


    private void setUpView(){

        saveDiaryActivities = findViewById(R.id.saveDiaryActivities);
        saveDiaryActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Try to save
                String timeStamp = timeController.timeStamp();
                DbTimeDistribution dbTimeDistribution = new DbTimeDistribution(TimeDistribution.this);
                int count = 1;
                boolean allRegistedInserted = true;


                //Only save the renderized
                for(int i=0;i<arrayListItemTimeInversions.size();i++){
                    try {
                        String hour = timeDistributionAdapter.getItemX(i).getHour();
                        String activity = timeDistributionAdapter.getItemX(i).getSpinner().getSelectedItem().toString().trim();

                        Long id = dbTimeDistribution.insertDiaryActivity(timeStamp, hour, activity);

                        if (id > 0) {
                            allRegistedInserted = allRegistedInserted && true;
                            count = count + 1;
                        } else {
                            allRegistedInserted = allRegistedInserted && false;
                        }

                    }catch (Exception e){

                    }

                }

                Toast.makeText(TimeDistribution.this, "Save: "+count, Toast.LENGTH_LONG).show();

            }
        });


    }


    public void createNewItemTimeInversionAdapter(int i){
        ItemTimeInversion itemTimeInversion = new ItemTimeInversion(getHour(i), "");
        arrayListItemTimeInversions.add(itemTimeInversion);
    }

    public ArrayList<String> getSpinerActivities(){

        DbTimeDistribution dbTimeDistribution = new DbTimeDistribution(TimeDistribution.this);
        List<String> dataControllerActivities = dbTimeDistribution.getActivitiesList();


        ArrayList<String> spinerOptions = new ArrayList<>();

        for(int i=0;i<dataControllerActivities.size();i++){
            spinerOptions.add(dataControllerActivities.get(i));
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

        return String.valueOf(hour12h)+formatAMPM;
    }
}