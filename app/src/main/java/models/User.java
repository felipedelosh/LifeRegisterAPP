package models;

public class User {

    private String username;
    private String sex;
    private int yearBirthDate;
    private int mountBirthDate;
    private int dayBirthDate;

    public User(String username, String sex, int yearBirthDate, int mountBirthDate, int dayBirthDate) {
        this.username = username;
        this.sex = sex;
        this.yearBirthDate = yearBirthDate;
        this.mountBirthDate = mountBirthDate;
        this.dayBirthDate = dayBirthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getYearBirthDate() {
        return yearBirthDate;
    }

    public void setYearBirthDate(int yearBirthDate) {
        this.yearBirthDate = yearBirthDate;
    }

    public int getMountBirthDate() {
        return mountBirthDate;
    }

    public void setMountBirthDate(int mountBirthDate) {
        this.mountBirthDate = mountBirthDate;
    }

    public int getDayBirthDate() {
        return dayBirthDate;
    }

    public void setDayBirthDate(int dayBirthDate) {
        this.dayBirthDate = dayBirthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", yearBirthDate=" + yearBirthDate +
                ", mountBirthDate=" + mountBirthDate +
                ", dayBirthDate=" + dayBirthDate +
                '}';
    }
}
