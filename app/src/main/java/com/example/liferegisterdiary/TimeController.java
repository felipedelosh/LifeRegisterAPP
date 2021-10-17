package com.example.liferegisterdiary;

import java.util.Calendar;

public class TimeController {


    public String [] daysOfWeek = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    public String [] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    public int [] numberOfDaysMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

    public TimeController(){

    }

    public String getCurrentYear(){
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public String getCurrentMonth(){
        return months[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public String getCurrentDayNumberOfMount(){
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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

    public int howManyDayHavMonht(){
        return  numberOfDaysMonths[Calendar.getInstance().get(Calendar.MONTH)];
    }

    public String timeStamp(){
        return getCurrentYear()+":"+getCurrentMonth()+":"+getCurrentDayNumberOfMount();
    }

    
    public String health(){
        return "Estoy en el time controller..."+timeStamp();
    }
}
