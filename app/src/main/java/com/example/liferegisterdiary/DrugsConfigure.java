package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.DrugsConfigureAdapter;
import models.ItemDrug;
import db.DbDrugsDiary;

public class DrugsConfigure extends AppCompatActivity {

    private ArrayList<ItemDrug> arrayListDrugs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_configure);

        Context context = this;
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(context);

        //Generate a list of drugs
        ListView listView = findViewById(R.id.listViewDrugs);
        arrayListDrugs = new ArrayList<>();
        DrugsConfigureAdapter drugsConfigureAdapter = new DrugsConfigureAdapter(arrayListDrugs, context);
        listView.setAdapter(drugsConfigureAdapter);

        List<String> allDrugs = dbDrugsDiary.getDrugsList();

        for(int i=0;i<allDrugs.size();i++){
            createNewItemDrug(allDrugs.get(i));
        }

    }

    private void createNewItemDrug(String nameDrug){
        ItemDrug itemDrug = new ItemDrug(nameDrug);
        arrayListDrugs.add(itemDrug);

    }
}