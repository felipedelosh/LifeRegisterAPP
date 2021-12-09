package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import db.PASSWORD;

public class Password extends AppCompatActivity {

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

        Button btnPassword = findViewById(R.id.btnPassword);
        btnPassword.setOnClickListener(v -> {
            String tempPassword = txtPassword.getText().toString();
            if(validatePassword(tempPassword)){
                txtPassword.setText("");
                enablePasswords(tempPassword);
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

        if(command.equals("backup")){
            String status = "";
            status = "Building BACKUP.sql\n";
            status = status + password.backupDatabase();
            txtInfoPassword.setText(status);

        }

        if(command.equals("restore")){
            String status = "";
            status = "restoring Database via BACKUP.sql\n";
            status = status + password.restore();
            txtInfoPassword.setText(status);

        }

        if(command.equals("formatWXP")){
            String status = "";
            status = password.eraseDatabase();
            txtInfoPassword.setText(status);
        }

        if(command.equals("chatbot")){
            txtInfoPassword.setText("launch ChatBot");
            launchChatBot();
        }


    }

    private void launchChatBot(){
        Intent launchChatBotView = new Intent(getApplicationContext(), ChatBOT.class);
        startActivity(launchChatBotView);
    }

    private boolean validatePassword(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }

}