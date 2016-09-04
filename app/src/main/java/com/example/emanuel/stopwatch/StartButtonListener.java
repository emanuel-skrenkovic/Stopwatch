package com.example.emanuel.stopwatch;

import android.widget.CompoundButton;
import android.widget.TextView;

public class StartButtonListener implements CompoundButton.OnCheckedChangeListener {

    private TextView timer;

    private TimeUpdater timeUpdater;

    public StartButtonListener(TextView timer) {
        this.timer = timer;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if(isChecked){
            timeUpdater = new TimeUpdater(timer);
            timeUpdater.execute();
        }
        else
        {
            timeUpdater.cancel(true);
        }
    }
}
