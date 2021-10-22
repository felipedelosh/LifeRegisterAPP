package Models;

public class ItemTimeInversion {

    public String hour;
    public String activity;

    public ItemTimeInversion(String hour, String activity) {
        this.hour = hour;
        this.activity = activity;
    }

    public String getHour() {
        return hour;
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
