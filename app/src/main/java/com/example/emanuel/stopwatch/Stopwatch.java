package com.example.emanuel.stopwatch;

public class Stopwatch {

    private enum State { RUNNING, PAUSED }

    private State state;

    private double startCount;
    private Time startTime;
    private Time currentTime;

    public Stopwatch() {
        state = State.PAUSED;
        startTime = new Time();
        currentTime = new Time();
    }

    public Time getFormattedTime() {
        if(state == State.RUNNING) {
            currentTime.setMinutes(getElapsedTime() / 6e10);
            currentTime.setSeconds(getElapsedTime() / 1e9);
            currentTime.setMilliseconds(getElapsedTime() / 1e6);
            return currentTime;
        }
        return null;
    }

    private double getElapsedTime() {
        return System.nanoTime() - startCount +
                (startTime.getMinutes() * 6e10) +
                (startTime.getSeconds() * 1e9) +
                (startTime.getMilliseconds() * 1e6);
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
            startTime = new Time(currentTime.toString());
        }
    }

    public void restart() {
        state = State.PAUSED;
        startTime = new Time();
        currentTime = new Time();
    }

    public void setStartTime(String time) {
        startTime = new Time(time);
    }
}
