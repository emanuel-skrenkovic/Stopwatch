package com.example.emanuel.stopwatch;

import android.os.AsyncTask;
import android.widget.TextView;

public class TimeUpdater extends AsyncTask<Void, String, Void> {

    private TextView timer;
    private Time startTime;

    public TimeUpdater(TextView timer) {
        this.timer = timer;
        startTime = new Time((String) timer.getText());
    }

    @Override
    protected Void doInBackground(Void... params) {
        long startCount = System.nanoTime();

        while(!(this.isCancelled())) {
            double timeDiff = System.nanoTime() - startCount +
                            (startTime.getMinutes() * 6e10) +
                            (startTime.getSeconds() * 1e9) +
                            (startTime.getMilliseconds() * 1e6);

            Time currentTime = new Time((timeDiff / 6e10),
                    (timeDiff / 1e9),
                    (timeDiff / 1e6));

            this.publishProgress(currentTime.toString());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        timer.setText(values[0]);
    }
}
