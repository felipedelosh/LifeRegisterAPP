package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import db.DbTAccount;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TAccountTest {

    @Test
    public void inserTAcc() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbTAccount dbTAccount = new DbTAccount(appContext);
        long id =  dbTAccount.insertTAccount("hoy", 99, "entrada", 100, 0);
        assertEquals(true,id>0);
    }

    @Test
    public void getTAcc() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbTAccount dbTAccount = new DbTAccount(appContext);
        long id =  dbTAccount.insertTAccount("hoy", 101, "entrada", 100, 0);
        List<String> data = dbTAccount.getTAccountByTimeStamp("hoy");
        assertEquals(false,data.isEmpty());
    }




}
