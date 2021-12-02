package models;

import android.widget.Spinner;

public class ItemTimeInversion {

    private String hour;
    private String activity;
    private Spinner spinner;


    public ItemTimeInversion(String hour, String activity) {
        this.hour = hour;
        this.activity = activity;
    }

    public String getHour() {
        return hour;
    }

    public void setSpinner(Spinner spinner){
        this.spinner = spinner;
    }

    public Spinner getSpinner(){
        return spinner;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
