package com.example.emanuel.stopwatch;

public class Time {

    private double minutes;
    private double seconds;
    private double milliseconds;

    public Time(double minutes,
                double seconds,
                double milliseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

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

    private String formatMinutes(Double minutes) {
        return format(Integer.toString(minutes.intValue()), 2);
    }

    private String formatSeconds(Double seconds, Double minutes) {
        if(seconds.intValue() >= 60){
            int result = seconds.intValue() - minutes.intValue() * 60;
            return format(Integer.toString(result), 2);
        }else{
            int result = seconds.intValue();
            return format(Integer.toString(result), 2);
        }
    }

    private String formatMilliseconds(Double milliseconds) {
        if(milliseconds > 1000) {
            int reducer = milliseconds.intValue() / 1000;
            int result = milliseconds.intValue() - reducer * 1000;
            return format(Integer.toString(result), 3);
        }else {
            String number = Integer.toString(milliseconds.intValue());
            return format(number, 3);
        }
    }

    private String format(String number, int numOfDigits) {
        return String.format("%" + numOfDigits + "s", number).replace(" ", "0");
    }

    public String toString() {
        return formatMinutes(minutes) +
                ":" +
                formatSeconds(seconds, minutes) +
                ":" +
                formatMilliseconds(milliseconds);
    }
}
