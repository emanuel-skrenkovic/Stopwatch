package com.example.emanuel.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timer = (TextView) findViewById(R.id.timer);

        ToggleButton startButton = (ToggleButton) findViewById(R.id.toggleButton);
        startButton.setOnCheckedChangeListener(new StartButtonListener(timer));

        Button pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new PauseButtonListener());
    }
}



