package com.example.liferegisterdiary;


import android.content.Context;

import java.io.File;
import java.sql.Time;
import java.util.Date;

/*
*
* This is a file and folder controller 2021
*
* Here you create and read files and folders.
*
* */
public class FileFolderController {

    Context context;
    public boolean mkdirStatus;
    public TimeController timeController;

    public FileFolderController(Context context, TimeController timeController) {

        this.context = context;
        this.timeController = timeController;
        mkdirStatus = true;


        //Create a folders to APP work
        createDataFolders();
    }

    public void createDataFolders(){

        //Root folder
        if(!existsDir("LifeRegisterDATA")){
            makeDir("LifeRegisterDATA");
        }

        //Profile
        if(!existsDir("LifeRegisterDATA/Profile")){
            makeDir("LifeRegisterDATA/Profile");
        }

        //Diary
        if(!existsDir("LifeRegisterData/Diary/personal_diary/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Diary/personal_diary/"+timeController.getCurrentYear());
        }
        if(!existsDir("LifeRegisterData/Diary/dream_diary/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Diary/dream_diary/"+timeController.getCurrentYear());
        }
        if(!existsDir("LifeRegisterData/Diary/gratitude_diary/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Diary/gratitude_diary/"+timeController.getCurrentYear());
        }
        if(!existsDir("LifeRegisterData/Diary/psicotrope_diary/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Diary/psicotrope_diary/"+timeController.getCurrentYear());
        }

        //Adiction
        //Necesitoi listar todas alas cosas a las que un ser humano se puede hacer adicto

        //Economy
        if(!existsDir("LifeRegisterData/Economy/Taccounts/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Economy/Taccounts/"+timeController.getCurrentYear());
        }
        if(!existsDir("LifeRegisterData/Economy/box/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Economy/box/"+timeController.getCurrentYear());
        }

        //Time invertion
        if(!existsDir("LifeRegisterData/TimeDistribution/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/TimeDistribution/"+timeController.getCurrentYear());
        }

        //Feelings
        if(!existsDir("LifeRegisterData/Feelings/"+timeController.getCurrentYear())){
            makeDir("LifeRegisterData/Feelings/"+timeController.getCurrentYear());
        }





    }

    public boolean makeDir(String folderName){

        try{
            String intStorageDir = this.context.getExternalFilesDir("") + "";
            File newFolder = new File(intStorageDir, folderName);
            return newFolder.mkdirs();

        }catch (Exception e){
            return false;
        }

    }

    /*
    *
    * */
    public boolean existsDir(String folderRoute){
        try{
            String intStorageDir = this.context.getExternalFilesDir("") + "";
            File newFolder = new File(intStorageDir, folderRoute);
            return newFolder.exists();
        }catch (Exception e){
            return false;
        }
    }


    public String health(){
        return "Estoy en el controlador de archivos y carpetas ... ";
    }

}
