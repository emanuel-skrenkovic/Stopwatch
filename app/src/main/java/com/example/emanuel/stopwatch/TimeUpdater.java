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

        while(!(this.isCancelled())){
            double timeDiff = System.nanoTime() - startCount +
                            (startTime.getMinutes() * 6e10) +
                            (startTime.getSeconds() * 1e9) +
                            (startTime.getMilliseconds() * 1e6);

            Double minutes = timeDiff / 6e10;
            Double seconds = timeDiff / 1e9;
            Double milliseconds = timeDiff / 1e6;

            this.publishProgress(formatMinutes(minutes) + ":" +
                    formatSeconds(seconds, minutes) + ":" +
                    formatMilliseconds(milliseconds));
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        timer.setText(values[0]);
    }

    private String formatMinutes(Double minutes){
        return format(Integer.toString(minutes.intValue()), 2);
    }

    private String formatSeconds(Double seconds, Double minutes) {
        if(seconds.intValue() >= 60){
            Integer result = seconds.intValue() - minutes.intValue() * 60;
            return format(result.toString(), 2);
        }else{
            Integer result = seconds.intValue();
            return format(result.toString(), 2);
        }
    }

    private String formatMilliseconds(Double milliseconds) {
        if(milliseconds > 1000) {
            int reducer = milliseconds.intValue() / 1000;
            Integer result = milliseconds.intValue() - reducer * 1000;
            return format(result.toString(), 3);
        }else{
            String number = Integer.toString(milliseconds.intValue());
            return format(number, 3);
        }
    }

    private String format(String number, int numOfDigits) {
        return String.format("%" + numOfDigits + "s", number).replace(" ", "0");
    }
}
