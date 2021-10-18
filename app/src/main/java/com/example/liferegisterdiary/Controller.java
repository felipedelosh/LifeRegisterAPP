package com.example.liferegisterdiary;


import android.content.Context;

import java.sql.Time;

/*
* This is a main controller of aplication.
* MVC
* All controllers invoke here
* */
public class Controller {

    Context context;

    TimeController timeController;
    FileFolderController fileFolderController;

    public Controller(Context context){

        this.context = context;

        timeController = new TimeController();
        fileFolderController = new FileFolderController(context, timeController);

    }

    public String health(){

        return fileFolderController.health();

    }


}
