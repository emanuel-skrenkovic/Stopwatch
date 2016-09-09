package com.example.emanuel.stopwatch;

public class Time {

    private double minutes;
    private double seconds;
    private double milliseconds;

    public Time(String time) {
        String[] buffer = time.split(":");
        this.minutes = Double.parseDouble(buffer[0]);
        this.seconds = Double.parseDouble(buffer[1]);
        this.milliseconds = Double.parseDouble(buffer[2]);
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
