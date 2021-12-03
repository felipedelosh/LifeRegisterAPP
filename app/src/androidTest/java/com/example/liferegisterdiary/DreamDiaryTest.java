package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import db.DbDreamDiaryPage;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DreamDiaryTest {

    @Test
    public void inserPage() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(appContext);
        long id = dbDreamDiaryPage.insertDreamDiary("NewTitle", 2021, "Fecha", "Bla bla bla");
        assertEquals(true,id>0);
    }

    @Test
    public void getPage() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbDreamDiaryPage dbDreamDiaryPage = new DbDreamDiaryPage(appContext);
        long id = dbDreamDiaryPage.insertDreamDiary("NewTitle", 2021, "Fecha", "Bla bla bla");
        List<String> pages = dbDreamDiaryPage.getPagesDreamDiary("NewTitle");
        assertEquals(true,pages.size()>0);
    }

}