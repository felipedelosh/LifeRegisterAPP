package com.example.liferegisterdiary;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import db.DbDreamDiaryPage;
import db.DbDrugsDiary;
import db.DbTimeDistribution;
import db.DbUser;

/*
* This is a main controller of aplication.
* MVC
* All controllers invoke here
* */
public class Controller implements Parcelable {

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
        fileFolderController = new FileFolderController(context, timeController);
        databaseController = new DatabaseController(context);
        database = databaseController.getWritableDatabase();
        user = new DbUser(context);


        //Generate a backgroung image
        paintBgImage();
        //Charge a list of drugs
        insertDrugsList();
        //Charge a list of activities
        insertActivitiesList();
    }

    protected Controller(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Controller> CREATOR = new Creator<Controller>() {
        @Override
        public Controller createFromParcel(Parcel in) {
            return new Controller(in);
        }

        @Override
        public Controller[] newArray(int size) {
            return new Controller[size];
        }
    };

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
        imgBG.setImageResource(R.drawable.bg_julia);
    }

    public void insertDrugsList(){
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(context);

        if (dbDrugsDiary.getDrugsList().size() == 0){

            String [] drugsList = {"Alcohol",
                    "Cannabis",
                    "Cocaína",
                    "Éxtasis",
                    "Juego",
                    "Nicotina",
                    "Pornografia"
            };


            for(int i = 0; i < drugsList.length; i++){
                Long id = dbDrugsDiary.insertDrug(drugsList[i]);
            }
        }
    }

    public void insertActivitiesList(){
        DbTimeDistribution dbTimeDistribution = new DbTimeDistribution(context);

        if (dbTimeDistribution.getActivitiesList().size() == 0){

            String [] ActitivitiesList = {
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


            for(int i = 0; i < ActitivitiesList.length; i++){
                Long id = dbTimeDistribution.insertActivity(ActitivitiesList[i]);
            }
        }
    }




    public String health(){
        return timeController.timeStampH();
    }

    public void popPupMesage(Context context,String txt){
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
    }
}
