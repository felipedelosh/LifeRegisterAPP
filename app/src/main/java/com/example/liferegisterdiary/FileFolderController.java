package com.example.liferegisterdiary;


import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


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
    //Routes
    private final String ROOTAPP = "LifeRegisterDATA";
    private final String PROFILEROUTE = "LifeRegisterDATA/Profile";
    private final String PERSONALDIARYROUTE = "LifeRegisterData/Diary/personal_diary";
    private final String DREAMDIARYROUTE = "LifeRegisterData/Diary/dream_diary";
    private final String GRATITUDEDIARYROUTE = "LifeRegisterData/Diary/gratitude_diary";
    private final String PSICOTROPEDIARYROUTE = "LifeRegisterData/Diary/psicotrope_diary";
    //Adiction
    //Necesitoi listar todas alas cosas a las que un ser humano se puede hacer adicto
    private final String TACCOUNTSROUTE = "LifeRegisterData/Economy/Taccounts";
    private final String BOXROUTE = "LifeRegisterData/Economy/box";
    private final String TIMEDISTRIBUTIONROUTE = "LifeRegisterData/TimeDistribution";
    private final String FEELINGROUTE = "LifeRegisterData/Feelings";


    public FileFolderController(Context context, TimeController timeController) {

        this.context = context;
        this.timeController = timeController;
        mkdirStatus = true;


        //Create a folders to APP work
        createDataFolders();
    }

    public void createDataFolders(){

        //Root folder
        if(!existsDir(ROOTAPP)){
            makeDir(ROOTAPP);
        }

        //Profile
        if(!existsDir(PROFILEROUTE)){
            makeDir(PROFILEROUTE);
        }

        //Diary
        if(!existsDir(PERSONALDIARYROUTE+"/"+timeController.getCurrentYear())){
            makeDir( PERSONALDIARYROUTE+"/"+timeController.getCurrentYear());
        }
        if(!existsDir(DREAMDIARYROUTE+"/"+timeController.getCurrentYear())){
            makeDir(DREAMDIARYROUTE+"/"+timeController.getCurrentYear());
        }
        if(!existsDir(GRATITUDEDIARYROUTE+"/"+timeController.getCurrentYear())){
            makeDir(GRATITUDEDIARYROUTE+"/"+timeController.getCurrentYear());
        }
        if(!existsDir(PSICOTROPEDIARYROUTE+"/"+timeController.getCurrentYear())){
            makeDir(PSICOTROPEDIARYROUTE+"/"+timeController.getCurrentYear());
        }

        //Adiction
        //Necesitoi listar todas alas cosas a las que un ser humano se puede hacer adicto

        //Economy
        if(!existsDir(TACCOUNTSROUTE+"/"+timeController.getCurrentYear())){
            makeDir(TACCOUNTSROUTE+"/"+timeController.getCurrentYear());
        }
        if(!existsDir(BOXROUTE+"/"+timeController.getCurrentYear())){
            makeDir(BOXROUTE+"/"+timeController.getCurrentYear());
        }

        //Time invertion
        if(!existsDir(TIMEDISTRIBUTIONROUTE+"/"+timeController.getCurrentYear())){
            makeDir(TIMEDISTRIBUTIONROUTE+"/"+timeController.getCurrentYear());
        }

        //Feelings
        if(!existsDir(FEELINGROUTE+"/"+timeController.getCurrentYear())){
            makeDir(FEELINGROUTE+"/"+timeController.getCurrentYear());
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

        String sms = "Estoy en el controlador de archivos y carpetas ... \n\n";

        //Try to write
        if(writeFile(ROOTAPP, "loco", "txt", "Estoy en el loco.txt")){
           sms = sms + "Almacene el archivo.txt\n\n";
           sms = sms + "El archivo dice:\n" + readFile("","loco.txt");

        }else{
            sms = sms + "Error Guardando txt";
        }

        return sms;
    }

    public boolean writeFile(String path, String filename, String extension, String text){
        try{
            OutputStreamWriter newFile = new OutputStreamWriter(context.openFileOutput(filename+"."+extension, Context.MODE_PRIVATE));
            newFile.write(text);
            newFile.flush();
            newFile.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String readFile(String path, String filename){
        try{
            InputStreamReader reader = new InputStreamReader(context.openFileInput(path+filename));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            String txt = "";
            while (line != null){
                txt = txt + line + "\n";
                line = br.readLine();
            }
            br.close();
            reader.close();
            return txt;
        }catch (Exception e){
            return "";
        }
    }

}
