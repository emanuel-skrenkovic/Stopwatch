package com.example.emanuel.stopwatch;

public class Time {

    private double minutes;
    private double seconds;
    private double milliseconds;

    public Time() {
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
    }

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

    public void setMinutes(double minutes){
        this.minutes = minutes;
    }

    public double getSeconds(){
        return seconds;
    }

    public void setSeconds(double seconds){
        this.seconds = seconds;
    }

    public double getMilliseconds(){
        return milliseconds;
    }

    public void setMilliseconds(double milliseconds){
        this.milliseconds = milliseconds;
    }

    private String formatMinutes(Double minutes) {
        return format(Integer.toString(minutes.intValue()), 2);
    }

    private String formatSeconds(Double seconds, Double minutes) {
        int result = (seconds.intValue() >= 60)
                ? seconds.intValue() - minutes.intValue() * 60
                : seconds.intValue();

        return format(Integer.toString(result), 2);
    }

    private String formatMilliseconds(Double milliseconds) {
        int result = (milliseconds > 1000)
                ? milliseconds.intValue() - (milliseconds.intValue() / 1000) * 1000
                : milliseconds.intValue();

        return format(Integer.toString(result), 3);
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
