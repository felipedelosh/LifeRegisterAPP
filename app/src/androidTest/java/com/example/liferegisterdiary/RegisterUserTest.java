package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import db.DbUser;
import models.User;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterUserTest {

    @Test
    public void registerUser() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbUser dbuser = new DbUser(appContext);
        Long id = dbuser.insertUser("test", "sex", 3333, 8, 99);
        assertEquals(true, id>0);
    }

    @Test
    public void userIsRegisted() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbUser dbuser = new DbUser(appContext);
        Long id = dbuser.insertUser("test", "sex", 3333, 8, 99);
        assertEquals(true, dbuser.userIsregisted());
    }


    @Test
    public void editUser() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbUser dbuser = new DbUser(appContext);
        Long id = dbuser.insertUser("test", "sex", 3333, 8, 99);
        id = dbuser.editUser("test1", "sex1", 33332, 82, 992);
        assertEquals(true, id>0);
    }

    @Test
    public void getUser() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbUser dbuser = new DbUser(appContext);
        Long id = dbuser.insertUser("test", "sex", 3333, 8, 99);
        User user = dbuser.getUser();
        assertEquals(true, user!=null);
    }

}