package com.example.liferegisterdiary;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import db.DbBox;
import db.DbDreamDiaryPage;
import db.DbDrugsDiary;
import db.DbFeeling;
import db.DbTimeDistribution;
import db.DbUser;

/*
* This is a main controller of aplication.
* MVC
* All controllers invoke here
* */
public class Controller{

    Context context;

    //Images
    ImageView imgBG;

    TimeController timeController;
    FileFolderController fileFolderController;
    DatabaseController databaseController;
    SQLiteDatabase database;

    //Database Objects
    DbUser user;

    public Controller(Context context, ImageView imgBG){

        this.context = context;
        this.imgBG = imgBG;

        timeController = new TimeController();
        databaseController = new DatabaseController(context);
        database = databaseController.getWritableDatabase();
        user = new DbUser(context);


        //Generate a backgroung image
        paintBgImage();
        //Charge a list of drugs
        insertDrugsList();
        //Charge a list of activities
        insertActivitiesList();
        //Charge a list of feelings
        insertFeelingsList();
    }

    protected Controller(Parcel in) {
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
        return timeController.timeStampH()+"\n"+timeController.timeStamp();
    }

    public void popPupMesage(Context context,String txt){
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
    }
}
