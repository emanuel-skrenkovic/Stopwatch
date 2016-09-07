package com.example.emanuel.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private boolean wasRunning = false;
    private ToggleButton startButton;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);

        startButton = (ToggleButton) findViewById(R.id.toggleButton);
        startButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private TimeUpdater timeUpdater;

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

        if(savedInstanceState != null){
            timer.setText(savedInstanceState.getCharSequence("time"));
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            startButton.setChecked(savedInstanceState.getBoolean("isRunning"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("isRunning",startButton.isChecked());
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        savedInstanceState.putCharSequence("time",timer.getText());
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning)
            startButton.setChecked(true);
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = startButton.isChecked();
        startButton.setChecked(false);
    }
}



