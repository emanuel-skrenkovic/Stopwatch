package com.example.emanuel.stopwatch;

import android.os.AsyncTask;
import android.os.Handler;
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
    private TimeUpdater timeUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        startButton = (ToggleButton) findViewById(R.id.toggleButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);

        startButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    timeUpdater = new TimeUpdater(timer);
                    timeUpdater.execute();
                }
                else {
                    timeUpdater.cancel(true);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startButton.isChecked())
                    startButton.setChecked(false);

                (new Handler()).post(new Runnable() {
                    @Override
                    public void run(){
                        timer.setText(R.string.zero_time);
                    }

                });
            }
        });

        if(savedInstanceState != null) {
            timer.setText(savedInstanceState.getCharSequence("time"));
            startButton.setChecked(savedInstanceState.getBoolean("isChecked"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("isChecked",startButton.isChecked());
        savedInstanceState.putCharSequence("time",timer.getText());
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
        if(timeUpdater != null && timeUpdater.getStatus() == AsyncTask.Status.RUNNING)
            timeUpdater.cancel(true);
    }
}



