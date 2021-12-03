package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import db.DbBox;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BoxTest {

    @Test
    public void inserBoxLitle() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbBox dbbox = new DbBox(appContext);
        long id =  dbbox.insertBoxLitleCount("hoy", 100);
        assertEquals(true,id>0);
    }

    @Test
    public void inserBoxBig() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbBox dbbox = new DbBox(appContext);
        long id =  dbbox.insertBoxBigCount("hoy", 100);
        assertEquals(true,id>0);
    }

    @Test
    public void getBoxLitle() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbBox dbbox = new DbBox(appContext);
        long id =  dbbox.insertBoxLitleCount("hoy", 100);
        List<Integer> data = dbbox.getAllCurrentValuesOfLitleBox("hoy");
        assertEquals(false,data.isEmpty());
    }

    @Test
    public void getBoxBig() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbBox dbbox = new DbBox(appContext);
        long id =  dbbox.insertBoxBigCount("hoy", 100);
        List<Integer> data = dbbox.getAllCurrentValuesOfBigBox("hoy");
        assertEquals(false,data.isEmpty());
    }



}



