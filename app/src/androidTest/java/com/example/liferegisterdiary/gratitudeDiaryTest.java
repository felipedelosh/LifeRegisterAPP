package com.example.liferegisterdiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

import db.DbGratitudeHistory;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class gratitudeDiaryTest {

    @Test
    public void inserPage() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbGratitudeHistory dbPersonalDiaryPage = new DbGratitudeHistory(appContext);
        long id = dbPersonalDiaryPage.insertGratirudeHistory("fecha", "bla bla bla");
        assertEquals(true,id>0);
    }

}
