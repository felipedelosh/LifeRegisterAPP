package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DbTAccount;

public class SearchMoney extends AppCompatActivity {

    private EditText txtSearchMoney;
    private TextView txtSearchMoneyOutPut;
    private DbTAccount dbTAccount;
    private List<String> listOfEconomyValuesBedit;
    private List<String> listOfEconomyValuesCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_money);

        dbTAccount = new DbTAccount(this);
        listOfEconomyValuesBedit = new ArrayList<>();
        listOfEconomyValuesCredit = new ArrayList<>();
        txtSearchMoneyOutPut = findViewById(R.id.txtSearchMoneyOutPut);
        txtSearchMoney = findViewById(R.id.txtSearchMoney);

        setUpView();
    }

    private void setUpView() {
        Button btnSearchTAccountsMouves = findViewById(R.id.btnSearchTAccountsMouves);
        btnSearchTAccountsMouves.setOnClickListener(v -> {
            if(validateText(txtSearchMoney.getText().toString())){
                printMoneyOutPut();
                txtSearchMoney.setText("");
            }
        });
    }

    /****
     * put a detail
     */
    public void printMoneyOutPut(){
        HashMap<String, Integer> information = dbTAccount.getTAccountsByDetail(txtSearchMoney.getText().toString());

        String output = "";
        if(information.size()>0){


            for(String key: information.keySet()){
                //Put key name
                output  = output + key + " ... " + information.get(key) +  "\n";
            }

            txtSearchMoneyOutPut.setText(output);
        }else{
            txtSearchMoneyOutPut.setText("Error 404 ... Not Info :(");
        }
    }

    //Valitate if the text is rigth
    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }
}