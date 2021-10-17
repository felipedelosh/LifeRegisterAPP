package com.example.liferegisterdiary;


/*
* This is a main controller of aplication.
* MVC
* All controllers invoke here
* */
public class Controller {

    TimeController timeController;

    public Controller(){

        timeController = new TimeController();

    }

    public String health(){
        return timeController.health();
    }


}
