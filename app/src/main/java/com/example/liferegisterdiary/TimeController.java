package com.example.liferegisterdiary;


import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeController{


    public String [] daysOfWeek = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    public String [] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    public int [] numberOfDaysMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

    public TimeController(){

    }

    /*
    * Enter a mounth name and retrun the number of days of these mounth
    *
    * */
    public int getNumberDaysOfMounthX(String monthname){
        int k = 0;
        while(monthname != months[k]){
            k = k + 1;
        }

        return numberOfDaysMonths[k];
    }

    /*
    * Enter a name of mount and return a number
    * En = 0
    * Feb = 1
    * */
    public int getNomberOfMountX(String monthname){
        int k = 0;
        while(monthname != months[k]){
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

        List<String> currentM = new ArrayList<String>();

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
        return String.valueOf(hour)+":"+String.valueOf(minut);
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
    * Return a hour 10 min rounded
    * example 1:36 = 1:30; 1:00 = 1:00, 1:12 = 1:10
    * */
    public String getCurrentHour24ROUNDED15(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minut = Calendar.getInstance().get(Calendar.MINUTE);

        minut = 10*(minut/10);

        return String.valueOf(hour)+":"+String.valueOf(minut);
    }

    public int howManyDayHavMonht(){
        return  numberOfDaysMonths[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public String timeStamp(){
        return getCurrentYear()+":"+getCurrentMonth()+":"+getCurrentDayNumberOfMount();
    }

    public String timeStampH(){
        return getCurrentYear()+":"+getCurrentMonth()+":"+getCurrentDayNumberOfMount()+":"+getCurrentHour24ROUNDED15();
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

    public int getDaysOfMounthX(int m){
        return numberOfDaysMonths[m];
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public void setNumberOfDaysMonths(int[] numberOfDaysMonths) {
        this.numberOfDaysMonths = numberOfDaysMonths;
    }

    public String health(){
        return "Estoy en el time controller..."+timeStamp();
    }
}
