package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import db.DbBox;

public class BoxEconomy extends AppCompatActivity {

    private DbBox dbBox;
    private TimeController timeController;
    private EditText txtInsertLitleBoxValue;
    private EditText txtInsertBigBoxValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_economy);



        dbBox = new DbBox(BoxEconomy.this);
        timeController = new TimeController();
        txtInsertLitleBoxValue = findViewById(R.id.txtInsertLitleBoxValue);
        txtInsertBigBoxValue = findViewById(R.id.txtInsertBigBoxValue);

        setup();
    }

    private void setup() {

        Button btnUpdateLitleBox = findViewById(R.id.btnUpdateLitleBox);
        btnUpdateLitleBox.setOnClickListener(v -> {

           String value = txtInsertLitleBoxValue.getText().toString();

           if(validatevalue(value)){

               String timeStampH = timeController.timeStampH();
               Long id = dbBox.insertBoxLitleCount(timeStampH, Integer.parseInt(value));
               if(id>0){
                   txtInsertLitleBoxValue.setText("");
                   Toast.makeText(BoxEconomy.this, "save", Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(BoxEconomy.this, "Not save", Toast.LENGTH_LONG).show();
               }


           }

        });
        Button btnUpdateBigBox = findViewById(R.id.btnUpdateBigBox);
        btnUpdateBigBox.setOnClickListener(v -> {
            String value = txtInsertBigBoxValue.getText().toString();

            if(validatevalue(value)){

                String timeStampH = timeController.timeStampH();
                Long id = dbBox.insertBoxBigCount(timeStampH, Integer.parseInt(value));
                if(id>0){
                    txtInsertBigBoxValue.setText("");
                    Toast.makeText(BoxEconomy.this, "Save", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(BoxEconomy.this, "Not save", Toast.LENGTH_LONG).show();
                }


            }
        });



    }


    public boolean validatevalue(String value){
        if(!value.equals("") && !value.trim().equals("") && value.trim().length() > 0){
            try{
                int v = Integer.parseInt(value);
                return v >= 0;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }
}