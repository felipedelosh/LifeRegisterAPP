package com.example.liferegisterdiary;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeController{


    private static final String [] daysOfWeek = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    private static final String [] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private static final int [] numberOfDaysMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

    public TimeController(){
        //Do nothing
    }

    /*
    * Enter a mounth name and retrun the number of days of these mounth
    *
    * */
    public int getNumberDaysOfMounthX(String monthname){
        int k = 0;
        while(!monthname.equals(months[k])){
            k = k + 1;
        }

        return numberOfDaysMonths[k];
    }

    /*
    * Enter a name of mount and return a number
    * En = 0
    * Feb = 1
    * */
    public int getNumberOfMountX(String monthname){
        int k = 0;
        while(!monthname.equals(months[k])){
            k = k + 1;
        }
        return k;
    }

    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public String[] getMonths() {
        return months;
    }

    public int[] getNumberOfDaysMonths() {
        return numberOfDaysMonths;
    }

    /***
     * Return a months to past + current month
     * @return [monthx, MonthX, ... Currently monthX]
     */
    public List<String> getCurrentMonths(){

        List<String> currentM = new ArrayList<>();

        int nroCurrentM = Calendar.getInstance().get(Calendar.MONTH);

        if(nroCurrentM>0){
            for(int i=0;i<=nroCurrentM;i++){
                currentM.add(months[i]);
            }
        }else{
            currentM.add(months[0]);
        }

        return  currentM;
    }

    public String getCurrentYear(){
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public String getCurrentMonth(){
        return months[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public int getNumberOfCurrentMounth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public int getCurrentDayNumberOfMount(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    //Data error
    public String getCurrentDayName(){
        return daysOfWeek[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];
    }

    public String getCurrentHour24(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minut = Calendar.getInstance().get(Calendar.MINUTE);
        return hour+":"+minut;
    }

    public int howManyYearsHavPassed(int day, int mounth, int year){

        int k = Calendar.getInstance().get(Calendar.YEAR) - year;



        if(mounth > Calendar.getInstance().get(Calendar.MONTH) ){
            k = k + 1;
        }

        if(mounth == Calendar.getInstance().get(Calendar.MONTH) ) {

            if(day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
                k = k + 1;
            }
        }

        return k;
    }

    /*
    * Return a hour 5 min rounded
    * example 1:36 = 1:35; 1:00 = 1:00, 1:12 = 1:10
    * */
    public String getCurrentHour24ROUNDED5(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minut = Calendar.getInstance().get(Calendar.MINUTE);

        minut = 5*(minut/5);

        return hour+":"+minut;
    }

    public int howManyDayHavMonht(){
        return  numberOfDaysMonths[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public String timeStamp(){
        return getCurrentYear()+":"+getCurrentMonth()+":"+getCurrentDayNumberOfMount();
    }

    public String timeStampH(){
        return getCurrentYear()+":"+getCurrentMonth()+":"+getCurrentDayNumberOfMount()+":"+getCurrentHour24ROUNDED5();
    }

    public int getDaysOfCurrentMounth(){
        return numberOfDaysMonths[Calendar.getInstance().get(Calendar.MONTH)];
    }

    /*
    * return a list int to most commond years to birddate
    * */
    public int [] getCommonYears(){

        int [] list = new int[100];

        for(int i=0; i < 100; i++){
            list[i] = Calendar.getInstance().get(Calendar.YEAR) - i;
        }

        return list;
    }


    /***
     * Retrun a hours to pased
     * Enter a Date
     * YYYY:NameMont:DD:24H:Minut
     * Compare with current date and return
     * @param date String >> YYYY:NameMont:DD:24H:Minut
     * @return The hours is containt in
     */
    public Long howManyHoursPased(String date){
        try {
            String [] dateToCompareInfo = date.split(":");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
            String strCurrentDate = getCurrentYear()+":"+getNumberOfCurrentMounth()+":"+getCurrentDayNumberOfMount()+":"+getCurrentHour24();
            Date currentDATE = sdf.parse(strCurrentDate);
            String strAnotherDate = dateToCompareInfo[0]+":"+getNumberOfMountX(dateToCompareInfo[1])+":"+dateToCompareInfo[2]+":"+dateToCompareInfo[3]+":"+dateToCompareInfo[4];
            Date anotherDATE = sdf.parse(strAnotherDate);

            //Return date
            Long diff = currentDATE.getTime() - anotherDATE.getTime();

            long seg = diff / 1000;
            long min = seg / 60;
            long hours = min / 60;

            return  hours;
        } catch (ParseException e) {
            return 0L;
        }
    }



    public int getDaysOfMounthX(int m){
        return numberOfDaysMonths[m];
    }

    public String health(){
        return "Estoy en el time controller..."+timeStamp();
    }
}
