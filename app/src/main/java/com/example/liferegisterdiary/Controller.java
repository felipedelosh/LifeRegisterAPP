package com.example.liferegisterdiary;


import android.content.Context;
import android.widget.ImageView;

import java.sql.Time;

/*
* This is a main controller of aplication.
* MVC
* All controllers invoke here
* */
public class Controller {

    Context context;

    //Images
    ImageView imgBG;

    TimeController timeController;
    FileFolderController fileFolderController;

    public Controller(Context context, ImageView imgBG){

        this.context = context;
        this.imgBG = imgBG;

        timeController = new TimeController();
        fileFolderController = new FileFolderController(context, timeController);


        //Generate a backgroung image
        paintBgImage();

    }

    /*
    * return if exists user profile information
    * */
    public boolean userIsRegister(){
        return false;
    }

    public void paintBgImage(){
        imgBG.setImageResource(R.drawable.bg_julia);
    }

    public String health(){

        return timeController.health();

    }


}
