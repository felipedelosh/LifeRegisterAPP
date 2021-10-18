package com.example.liferegisterdiary;


import android.content.Context;

import java.io.File;

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

    public FileFolderController(Context context){

        this.context = context;
        mkdirStatus = true;

    }

    public void createDataFolders(){



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
        if(makeDir("LifeRegisterDATA")) {
            return "Estoy en el controlador de archivos y carpetas... carpeta principal creada" ;
        }else{
            return "Estoy en el controlador de archivos y carpetas... capeta principal no creada";
        }
    }

}
