package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_distribution);

        context = this;

        //Get all Activities
        ArrayList<String> spinerOptions = getSpinerActivities();

        //Generate a time distribution items
        listViewTimeInversion = findViewById(R.id.listViewTimeInversion);
        arrayListItemTimeInversions = new ArrayList<>();
        timeDistributionAdapter = new TimeDistributionAdapter(arrayListItemTimeInversions, context, getSpinerActivities());
        listViewTimeInversion.setAdapter(timeDistributionAdapter);

        for(int i=0;i<24;i++){
            createNewItemTimeInversionAdapter();
        }
    }

    public void createNewItemTimeInversionAdapter(){
        ItemTimeInversion itemTimeInversion = new ItemTimeInversion("", "");
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
}