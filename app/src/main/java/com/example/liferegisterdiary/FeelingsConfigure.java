package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.FeelingsConfigureAdapter;
import models.ItemFeeling;
import db.DbFeeling;


public class FeelingsConfigure extends AppCompatActivity {

    private ArrayList<ItemFeeling> arrayListItemFeelings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_configure);

        Context context = this;
        DbFeeling dbFeeling = new DbFeeling(context);

        //Generate a list of feelings
        ListView listView = findViewById(R.id.listViewFeelings);
        arrayListItemFeelings = new ArrayList<>();
        FeelingsConfigureAdapter feelingsConfigureAdapter = new FeelingsConfigureAdapter(arrayListItemFeelings, context);
        listView.setAdapter(feelingsConfigureAdapter);

        List<String> allFeelings = dbFeeling.getFeelingsList();

        for(int i=0;i<allFeelings.size();i++){
            createNewItemFeeling(allFeelings.get(i));
        }

    }


    public void createNewItemFeeling(String nameFeeling){
        ItemFeeling itemFeeling = new ItemFeeling(nameFeeling);
        arrayListItemFeelings.add(itemFeeling);
    }
}