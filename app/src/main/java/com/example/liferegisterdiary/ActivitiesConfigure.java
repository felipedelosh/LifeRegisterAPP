package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.ActivitiesConfigureAdapter;
import models.ItemActivity;
import db.DbTimeDistribution;

public class ActivitiesConfigure extends AppCompatActivity {

    private ArrayList<ItemActivity> arrayListItemActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_configure);

        Context context = this;
        boolean posibleConnect = false;
        DbTimeDistribution dbTimeDistribution = null;



        try {
            dbTimeDistribution = new DbTimeDistribution(this);
            posibleConnect = true;
        }catch (Exception e){
            posibleConnect = false;
        }

        if(posibleConnect){
            //Generate a list of activities
            ListView listView = findViewById(R.id.listViewActivities);
            arrayListItemActivities = new ArrayList<>();
            ActivitiesConfigureAdapter activitiesConfigureAdapter = new ActivitiesConfigureAdapter(arrayListItemActivities, context);
            listView.setAdapter(activitiesConfigureAdapter);

            List<String> allActivities = dbTimeDistribution.getActivitiesList();

            for(int i=0;i<allActivities.size();i++){
                createNewItemActivityadapter(allActivities.get(i));
            }
        }

    }


    private void createNewItemActivityadapter(String activityName){
        ItemActivity itemActivity = new ItemActivity(activityName);
        arrayListItemActivities.add(itemActivity);
    }
}