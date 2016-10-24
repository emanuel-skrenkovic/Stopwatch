package com.example.emanuel.stopwatch;

public class Stopwatch {

    private enum State { RUNNING, PAUSED }

    private State state;

    private double startCount;
    private double pauseOffset = 0;

    public Stopwatch() {
        state = State.PAUSED;
    }

    public double getElapsedTime() {
        return System.nanoTime() - startCount + pauseOffset;
    }

    public void start() {
        if(state == State.PAUSED) {
            startCount = System.nanoTime();
            state = State.RUNNING;
        }
    }

    public void pause() {
        if(state == State.RUNNING) {
            state = State.PAUSED;
            pauseOffset = getElapsedTime();
        }
    }

    public void restart() {
        state = State.PAUSED;
        pauseOffset = 0;
    }

    public boolean isRunning() {
        return (state == State.RUNNING);
    }

    public void setStartTime(double offset) {
        this.pauseOffset = offset;
    }

    public String getFormattedTime() {
        if(state == State.RUNNING) {
            return formatMinutes(getElapsedTime() / 6e10) +
                    ":" +
                    formatSeconds(getElapsedTime() / 1e9,
                            getElapsedTime() / 6e10) +
                    ":" +
                    formatMilliseconds(getElapsedTime() / 1e6);
        }
        return null;
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
}
