package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.DrugsConfigureAdapter;
import Models.ItemDrug;
import db.DbDrugsDiary;

public class DrugsConfigure extends AppCompatActivity {

    private DrugsConfigureAdapter drugsConfigureAdapter;
    private ArrayList<ItemDrug> arrayListDrugs;
    private Context context;
    private DbDrugsDiary dbDrugsDiary;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_configure);

        context = this;
        dbDrugsDiary = new DbDrugsDiary(context);

        //Generate a list of drugs
        listView = findViewById(R.id.listViewDrugs);
        arrayListDrugs = new ArrayList<>();
        drugsConfigureAdapter = new DrugsConfigureAdapter(arrayListDrugs, context);
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