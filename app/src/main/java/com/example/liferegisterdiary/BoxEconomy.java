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
    private Button btnUpdateLitleBox;
    private Button btnUpdateBigBox;
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

        btnUpdateLitleBox = findViewById(R.id.btnUpdateLitleBox);
        btnUpdateLitleBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String value = txtInsertLitleBoxValue.getText().toString();

               if(validatevalue(value)){

                   String timeStampH = timeController.timeStampH();
                   Long id = dbBox.insertBoxLitleCount(timeStampH, 100);
                   if(id>0){
                       txtInsertLitleBoxValue.setText("");
                       Toast.makeText(BoxEconomy.this, "accaaaa", Toast.LENGTH_LONG).show();
                   }else{
                       Toast.makeText(BoxEconomy.this, "Not save", Toast.LENGTH_LONG).show();
                   }


               }

            }
        });
        btnUpdateBigBox = findViewById(R.id.btnUpdateBigBox);
        btnUpdateBigBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = txtInsertBigBoxValue.getText().toString();

                if(validatevalue(value)){

                    String timeStampH = timeController.timeStampH();
                    Long id = dbBox.insertBoxBigCount(timeStampH, 100);
                    if(id>0){
                        txtInsertBigBoxValue.setText("");
                        Toast.makeText(BoxEconomy.this, "accaaaa", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(BoxEconomy.this, "Not save", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });



    }


    public boolean validatevalue(String value){
        if(value != "" && value.trim() != "" && value.trim().length() > 0){
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