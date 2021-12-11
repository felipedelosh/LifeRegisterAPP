package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

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

        try {
            username = u.getUsername();
            chatBotAgent = new ChatBotAgent(this, u);
        }catch (Exception e){
            u = new User("", "", 0, 0, 0);
            chatBotAgent = new ChatBotAgent(this, u);
        }

        lblChatBot = findViewById(R.id.lblChatBot);
        txtChatBot = findViewById(R.id.txtChatBot);

        //Vars
        chat = "";

        setupView();
        thinking();
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
                putUserSMSandSEND(sms);
                txtChatBot.setText("");
                refreshChat();
                sendMSMtoChatBot(sms.toLowerCase(Locale.ROOT));

            }

        });
    }

    /***
     * Erase all chat and repaint whit new elements
     */
    public void refreshChat(){
        lblChatBot.setText(chat);
    }

    /***
     * put user sms in conversation
     * and then send sms to chatbot
     * @param sms
     */
    public void putUserSMSandSEND(String sms){
        chat = chat + username + " -> " + timeController.getCurrentHour24() + "\n"  +"\"" + sms + "\"\n\n";
    }

    public void sendMSMtoChatBot(String sms){
        chatBotAgent.enterSMS(sms);
    }

    /***
     * if the chat bot response paint
     */
    public void putChatBotResponseInChat(){

        if(!chatBotAgent.getResponse().equals("")){
            chat = chat + "FEMBOT" + ":\n" + chatBotAgent.getResponse() + " - " + timeController.getCurrentHour24() + "\n\n";
            //Destroy SMS
            chatBotAgent.eraseSMS();
        }
    }

    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
    }

    /***
     * This is a Thread the machine take a desition:
     *
     * 1 -> Listen you
     * 2 -> make a question
     */
    public void thinking(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //refreshChat();
                    //Wait
                    try {
                        //Need Refresh Main Screem
                        runOnUiThread(() -> {
                            putChatBotResponseInChat();
                            refreshChat();
                            chatBotAgent.generateBehavior();
                        });

                        Thread.sleep(chatBotAgent.getTimeResponse());
                    }catch (Exception e){
                        //Do nothing
                    }
                }
            }


        }).start();
    }
}