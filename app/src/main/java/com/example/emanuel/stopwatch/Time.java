package com.example.emanuel.stopwatch;

public class Time {

    private String minutes;
    private String seconds;
    private String milliseconds;

    private String getMinutes(){
        return minutes;
    }

    private void setMinutes(String minutes){
        this.minutes = minutes;
    }

    private String getSeconds(){
        return seconds;
    }

    private void setSeconds(String seconds){
        this.seconds = seconds;
    }

    private String getMilliseconds(){
        return milliseconds;
    }

    private void setMilliseconds(String milliseconds){
        this.milliseconds = milliseconds;
    }
}
