package com.example.emanuel.stopwatch;

public class Stopwatch {

    private enum State { RUNNING, PAUSED }

    private State state;

    private double startCount;
    private double currentTime;
    private double pauseOffset;

    public Stopwatch() {
        state = State.PAUSED;
    }

    public void start() {
        if(state == State.PAUSED) {
            state = State.RUNNING;
            startCount = System.nanoTime();
        }
    }

    public void pause() {
        if(state == State.RUNNING) {
            state = State.PAUSED;
            pauseOffset = currentTime;
        }
    }

    public void restart() {
        state = State.PAUSED;
        pauseOffset = 0;
    }

    public void setStartTime(double offset) {
        this.pauseOffset = offset;
    }

    public double getPausedTime() {
        return pauseOffset;
    }

    public boolean isRunning() {
        return state == State.RUNNING;
    }

    public double getElapsedTime() {
        return System.nanoTime() - startCount + pauseOffset;
    }

    public String getFormattedTime() {
        if(state == State.RUNNING) {
            currentTime = getElapsedTime();
            return formatMinutes(currentTime / 6e10) +
                    ":" +
                    formatSeconds(currentTime / 1e9) +
                    ":" +
                    formatMilliseconds(currentTime / 1e6);
        }
        return null;
    }

    public String getFormattedPausedTime() {
        if(state == State.PAUSED) {
            return formatMinutes(pauseOffset / 6e10) +
                    ":" +
                    formatSeconds(pauseOffset / 1e9) +
                    ":" +
                    formatMilliseconds(pauseOffset / 1e6);
        }
        return null;
    }

    private String formatMinutes(Double minutes) {
        return format(Integer.toString(minutes.intValue()), 2);
    }

    private String formatSeconds(Double seconds) {
        return format(Integer.toString(
                (seconds.intValue() > 60)
                ? seconds.intValue() - (seconds.intValue() / 60) * 60
                : seconds.intValue()), 2);
    }

    private String formatMilliseconds(Double milliseconds) {
        return format(Integer.toString(
                (milliseconds > 1000)
                ? milliseconds.intValue() - (milliseconds.intValue() / 1000) * 1000
                : milliseconds.intValue()), 3);
    }

    private String format(String number, int numOfDigits) {
        return String.format("%" + numOfDigits + "s", number).replace(" ", "0");
    }
}
