package com.example.emanuel.stopwatch;

import android.os.AsyncTask;
import android.widget.TextView;

public class TimeUpdater extends AsyncTask<Void, String, Void> {

    private TextView timer;
    private boolean restart = false;
    private Stopwatch stopwatch;

    public TimeUpdater(TextView timer, Stopwatch stopwatch) {
        this.timer = timer;
        this.stopwatch = stopwatch;
    }

    @Override
    protected Void doInBackground(Void... params) {
        stopwatch.start();

        while(!(this.isCancelled()))
            this.publishProgress(stopwatch.getElapsedTime().toString());

        //stopwatch.pause();
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        timer.setText(values[0]);
    }

    @Override
    protected void onCancelled() {
        if(restart) {
            stopwatch.restart();
            timer.setText(R.string.zero_time);
        }
    }

    public void restart(boolean restart) {
        this.restart = restart;
    }
}
