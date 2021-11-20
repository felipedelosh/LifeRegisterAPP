package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ActivitiesConfigureAdapter;
import Models.ItemActivity;
import db.DbTimeDistribution;

public class ActivitiesConfigure extends AppCompatActivity {

    private ActivitiesConfigureAdapter activitiesConfigureAdapter;
    private ArrayList<ItemActivity> arrayListItemActivities;
    private Context context;
    private DbTimeDistribution dbTimeDistribution;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_configure);


        context = this;
        dbTimeDistribution = new DbTimeDistribution(this);

        //Generate a list of activities
        listView = findViewById(R.id.listViewActivities);
        arrayListItemActivities = new ArrayList<>();
        activitiesConfigureAdapter = new ActivitiesConfigureAdapter(arrayListItemActivities, context);
        listView.setAdapter(activitiesConfigureAdapter);

        List<String> allActivities = dbTimeDistribution.getActivitiesList();

        for(int i=0;i<allActivities.size();i++){
            createNewItemActivityadapter(allActivities.get(i));
        }


    }


    private void createNewItemActivityadapter(String activityName){
        ItemActivity itemActivity = new ItemActivity(activityName);
        arrayListItemActivities.add(itemActivity);
    }
}