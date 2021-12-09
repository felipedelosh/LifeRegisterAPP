package com.example.liferegisterdiary;


import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import db.DbDrugsDiary;
import db.DbFeeling;
import db.DbNotifications;
import db.DbTimeDistribution;
import db.DbUser;

/*
* This is a main controller of aplication.
* This is a AGENT thread
* MVC
* All controllers invoke here
* */
public class Controller{

    private Context context;

    //Images
    private ImageView imgBG;

    private TimeController timeController;

    //Database Objects
    private DbUser user;
    private DbNotifications dbNotifications;

    //Local variables
    private String outPutMessage; //To show a MAIN message

    public Controller(Context context, ImageView imgBG){

        this.context = context;
        this.imgBG = imgBG;

        timeController = new TimeController();
        user = new DbUser(context);
        dbNotifications = new DbNotifications(context);

        //init vars
        outPutMessage = "";


        //Generate a backgroung image
        paintBgImage();
        //Charge a list of drugs
        insertDrugsList();
        //Charge a list of activities
        insertActivitiesList();
        //Charge a list of feelings
        insertFeelingsList();
        //Generate Notifications
        insertNotifications();
    }

    public String getOutPutMessage() {
        return outPutMessage;
    }

    public void setOutPutMessage(String outPutMessage) {
        this.outPutMessage = outPutMessage;
    }

    public TimeController getTimeController(){
        return timeController;
    }

    /*
    * return if exists user profile information
    * */
    public boolean userIsRegister(){
        return user.userIsregisted();
    }

    public void paintBgImage(){
        imgBG.setImageResource(R.drawable.bg_random_01);
    }

    public void insertNotifications(){

        try{
            if(dbNotifications.getNotificationList().isEmpty()){

                String lasttime = timeController.timeStamp() + ":" + timeController.getCurrentHour24();

                String [] notifications = {
                        "chatbot"
                };

                for(int i=0;i<notifications.length;i++){
                    dbNotifications.insertNotification(notifications[i], lasttime);
                }
            }

        }catch (Exception e){
            //Nothing
        }
    }

    public void insertDrugsList(){
        try {
            DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(context);

            if (dbDrugsDiary.getDrugsList().isEmpty()){

                String [] drugsList = {
                        "Alcohol",
                        "Cannabis",
                        "Cocaína",
                        "Éxtasis",
                        "Juego",
                        "Nicotina",
                        "Pornografia"
                };


                for(int i = 0; i < drugsList.length; i++){
                    dbDrugsDiary.insertDrug(drugsList[i]);
                }
            }
        }catch (Exception e){
            //Nothing
        }
    }

    public void insertActivitiesList(){

        try{

            DbTimeDistribution dbTimeDistribution = new DbTimeDistribution(context);

            if (dbTimeDistribution.getActivitiesList().isEmpty()){

                String [] actitivitiesList = {
                        "alimentación",
                        "bicicleta",
                        "compras",
                        "construir",
                        "dormir",
                        "deporte",
                        "estudiar",
                        "FAPTIME",
                        "higiene personal",
                        "internet",
                        "ocio",
                        "leer",
                        "que haceres domesticos",
                        "peliculas",
                        "programar",
                        "redes sociales",
                        "sexo",
                        "trabajar",
                        "viajar",
                        "video juegos",
                        "vida social"
                };


                for(int i = 0; i < actitivitiesList.length; i++){
                    dbTimeDistribution.insertActivity(actitivitiesList[i]);
                }
            }

        }catch (Exception e){
            //Do nothing
        }

    }


    public void insertFeelingsList(){

        try{

            DbFeeling dbFeeling = new DbFeeling(context);

            if(dbFeeling.getFeelingsList().isEmpty()) {

                String[] feelingsList = {
                        "alegría",
                        "amor",
                        "tristeza",
                        "tranquilidad",
                        "ira",
                        "miedo",
                        "hostilidad",
                        "frustración",
                        "odio",
                        "culpa",
                        "celos",
                        "cansado",
                        "felicidad",
                        "esperanza",
                        "asco",
                        "motivado",
                        "suicida",
                        "orgullozo",
                        "vergüenza",
                        "drogado",
                        "trabado",
                        "alcoholizado",
                        "satisfecho"
                };

                for(int i = 0; i < feelingsList.length; i++){
                    dbFeeling.insertFeeling(feelingsList[i]);
                }

            }

        } catch (Exception e){
            //Do nothing
        }
    }


    public String health(){
        return "Estoy en el main controller";
    }

    public void popPupMesage(Context context,String txt){
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
    }

    /***
     * This methos is called x Seconds
     * and launch a activity if needed
     */
    public String verifyLaunchers(){
        //Get chatbot
        String lastTime = dbNotifications.getLastTimeOfNotification("chatbot");
        if(timeController.howManyHoursPased(lastTime)>2){
            //Refresh time
            String newlasttime = timeController.timeStamp() + ":" + timeController.getCurrentHour24();
            if(dbNotifications.editNotification("chatbot", newlasttime)){
                Intent launchChatBotView = new Intent(context.getApplicationContext(), ChatBOT.class);
                context.startActivity(launchChatBotView);
                return "Launching CHATBOT";
            }
        }

        return "Nothing TODO";
    }

}
