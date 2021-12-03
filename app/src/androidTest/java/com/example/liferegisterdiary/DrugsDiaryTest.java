package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import db.DbDrugsDiary;
import db.DbGratitudeHistory;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DrugsDiaryTest {

    @Test
    public void inserDrugTale() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(appContext);
        long id = dbDrugsDiary.insertDrugDiaryNote("hoy", "cannabis", "detoante", "resultado");
        assertEquals(true,id>0);
    }


    @Test
    public void inserDrugCount() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(appContext);
        long id =  dbDrugsDiary.insertDrugCount("hoy", "cannabis");
        assertEquals(true,id>0);
    }


    @Test
    public void getDrugInfo() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(appContext);
        List<String> lista = dbDrugsDiary.getDrugsList();
        assertEquals(false,lista.isEmpty());
    }



    //Part2
    @Test
    public void inserINDrugCount() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDrugsDiary dbDrugsDiary = new DbDrugsDiary(appContext);
        long id =  dbDrugsDiary.insertDrugCount("hoy", "cannabis");
        assertEquals(true,id>0);
    }

}
