package com.example.emanuel.stopwatch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private boolean wasRunning = false;
    private ToggleButton startButton;
    private TextView timer;
    private TimeUpdater updater;
    private Stopwatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        startButton = (ToggleButton) findViewById(R.id.toggleButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        stopwatch = new Stopwatch();

        startButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    updater = new TimeUpdater(timer, stopwatch);
                    updater.execute();
                }
                else {
                    if(updater != null)
                        updater.cancel(true);
                    stopwatch.pause();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updater != null)
                    updater.restart();
                if(startButton.isChecked()) {
                    startButton.setChecked(false);
                } else {
                    stopwatch.restart();
                    timer.setText(R.string.zero_time);
                }
            }
        });

        if(savedInstanceState != null) {
            timer.setText(savedInstanceState.getCharSequence("time"));
            stopwatch.setStartTime(savedInstanceState.getDouble("pauseOffset"));
            startButton.setChecked(savedInstanceState.getBoolean("isChecked"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("isChecked",startButton.isChecked());
        savedInstanceState.putDouble("pauseOffset", stopwatch.getElapsedTime());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasRunning)
            startButton.setChecked(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = startButton.isChecked();
        startButton.setChecked(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updater != null && updater.getStatus() == AsyncTask.Status.RUNNING)
            updater.cancel(true);
        if(stopwatch.isRunning())
            stopwatch.pause();
    }
}



