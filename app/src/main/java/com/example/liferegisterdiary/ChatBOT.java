package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import db.DbUser;
import femputadora.ChatBotAgent;
import models.User;

public class ChatBOT extends AppCompatActivity {

    private TextView lblChatBot;
    private EditText txtChatBot;
    private TimeController timeController;
    private ChatBotAgent chatBotAgent;
    private DbUser user;
    private String username;
    private String chat; // Save a messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        //Load controrllers
        timeController = new TimeController();
        user = new DbUser(this);
        User u = user.getUser();
        username = u.getUsername();
        chatBotAgent = new ChatBotAgent(this, u);

        lblChatBot = findViewById(R.id.lblChatBot);
        txtChatBot = findViewById(R.id.txtChatBot);

        //Vars
        chat = "";

        setupView();
    }

    private void setupView() {

        Button btnGoToMainMenu = findViewById(R.id.btnGoToMainMenu);
        btnGoToMainMenu.setOnClickListener(v -> {
            Intent launchMainMenu = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(launchMainMenu);
        });

        Button btnSendMSMChatBot = findViewById(R.id.btnSendMSMChatBot);
        btnSendMSMChatBot.setOnClickListener(v -> {

            String sms = txtChatBot.getText().toString();

            if(validateText(sms)){
                texting(sms);
                txtChatBot.setText("");
                lblChatBot.setText(chat);
            }

        });
    }

    public void texting(String sms){
        chat = chat + username + ":\n" + sms + " - " + timeController.getCurrentHour24() + "\n\n";
        chat = chat + "BOT" + ":\n" + chatBotResponse(sms) + " - " + timeController.getCurrentHour24() + "\n\n";
    }

    public String chatBotResponse(String sms){
        return chatBotAgent.responseSMS(sms);
    }

    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }
}