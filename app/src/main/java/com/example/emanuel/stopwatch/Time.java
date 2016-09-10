package com.example.emanuel.stopwatch;

public class Time {

    private double minutes;
    private double seconds;
    private double milliseconds;

    public Time(String time) {
        String[] temp = time.split(":");
        this.minutes = Double.parseDouble(temp[0]);
        this.seconds = Double.parseDouble(temp[1]);
        this.milliseconds = Double.parseDouble(temp[2]);
    }

    public double getMinutes(){
        return minutes;
    }

    public void setMinutes(long minutes){
        this.minutes = minutes;
    }

    public double getSeconds(){
        return seconds;
    }

    public void setSeconds(long seconds){
        this.seconds = seconds;
    }

    public double getMilliseconds(){
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds){
        this.milliseconds = milliseconds;
    }
}
