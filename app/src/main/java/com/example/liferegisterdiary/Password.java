package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import db.PASSWORD;

public class Password extends AppCompatActivity {

    private Button btnPassword;
    private EditText txtPassword;
    private TextView txtInfoPassword;
    private Context context;
    private PASSWORD password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        txtPassword = findViewById(R.id.txtPassword);
        txtInfoPassword = findViewById(R.id.txtInfoPassword);
        context = Password.this;
        password = new PASSWORD(context);


        setupView();
    }

    private void setupView() {

        btnPassword = findViewById(R.id.btnPassword);
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = txtPassword.getText().toString();
                if(validatePassword(password)){
                    txtPassword.setText("");
                    enablePasswords(password);
                }
            }
        });


    }

    public void enablePasswords(String command){

        if(command.equals("roboto")){
            String status = "";
            status = "Building DATABASE\n";
            status = status + password.roboto();
            txtInfoPassword.setText(status);

        }

        if(command.equals("felipedelosh")){
            String status = "";
            status = "Saludos Creador :)\n";
            status = status + password.felipedelosh();
            txtInfoPassword.setText(status);

        }

        if(command.equals("database")){
            String status = "";
            status = password.databaseCountRouts();
            txtInfoPassword.setText(status);

        }


    }


    private boolean validatePassword(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }

}