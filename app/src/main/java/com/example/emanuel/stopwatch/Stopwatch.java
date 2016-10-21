package com.example.emanuel.stopwatch;

import android.util.Log;

public class Stopwatch {

    private enum State { RUNNING, PAUSED }

    private State state;

    private long startCount;
    private Time startTime;
    private Time currentTime;
    private Time pausedTime;

    public Stopwatch() {
        state = State.PAUSED;
        startTime = new Time();
        currentTime = new Time();
    }

    public Time getElapsedTime() {
        if(state == State.RUNNING) {
            Log.i("startTime at resume", startTime.toString());
            double timeDiff = System.nanoTime() - startCount +
                    (startTime.getMinutes() * 6e10) +
                    (startTime.getSeconds() * 1e9) +
                    (startTime.getMilliseconds() * 1e6);

            currentTime.setMinutes(timeDiff / 6e10);
            currentTime.setSeconds(timeDiff / 1e9);
            currentTime.setMilliseconds(timeDiff / 1e6);
        }
        return currentTime;
    }

    public void start() {
        if(state == State.PAUSED) {
            startTime = (pausedTime != null) ? pausedTime : new Time();
            startCount = System.nanoTime();
            state = State.RUNNING;
        }
    }

    public void pause() {
        if(state == State.RUNNING) {
            pausedTime = currentTime;
            state = State.PAUSED;;
        }
    }

    public void restart() {
        state = State.PAUSED;
        startTime = new Time();
        currentTime = new Time();
    }
}
