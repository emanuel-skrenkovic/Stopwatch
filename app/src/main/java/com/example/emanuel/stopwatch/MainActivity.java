package com.example.emanuel.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

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
                    updater.cancel(true);
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
            startButton.setChecked(savedInstanceState.getBoolean("isChecked"));
            stopwatch.setStartTime(savedInstanceState.getDouble("startingTime"));
            timer.setText(savedInstanceState.getString("formattedTime"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(stopwatch.isRunning())
            stopwatch.pause();

        savedInstanceState.putDouble("startingTime", stopwatch.getPausedTime());
        savedInstanceState.putString("formattedTime", stopwatch.getFormattedPausedTime());
        savedInstanceState.putBoolean("isChecked",startButton.isChecked());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updater != null)
            updater.cancel(true);
    }
}



