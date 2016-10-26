package com.example.emanuel.stopwatch;

public class Stopwatch {

    private enum State { RUNNING, PAUSED }

    private State state;

    private double startCount;
    private double currentTime;
    private double pauseOffset = 0;

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

    public void getElapsedTime() {
        currentTime = System.nanoTime() - startCount + pauseOffset;
    }

    public String getFormattedTime() {
        if(state == State.RUNNING) {
            getElapsedTime();
            return formatMinutes(currentTime / 6e10) +
                    ":" +
                    formatSeconds(currentTime / 1e9) +
                    ":" +
                    formatMilliseconds(currentTime / 1e6);
        }
        return null;
    }

    public String getFormattedPausedTime() {
        return formatMinutes(pauseOffset / 6e10) +
                ":" +
                formatSeconds(pauseOffset / 1e9) +
                ":" +
                formatMilliseconds(pauseOffset / 1e6);
    }

    private String formatMinutes(Double minutes) {
        return format(Integer.toString(minutes.intValue()), 2);
    }

    private String formatSeconds(Double seconds) {
        int result = (seconds.intValue() > 60)
                ? seconds.intValue() - (seconds.intValue() / 60) * 60
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
