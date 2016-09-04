package com.example.emanuel.stopwatch;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class TimerChangedListener implements TextWatcher {
    TextView timer;

    public TimerChangedListener(TextView timer){
        this.timer = timer;
    }

    public void beforeTextChanged(CharSequence seq, int start, int count, int after){

    }

    public void onTextChanged(CharSequence seq, int start, int count, int after){

    }

    public void afterTextChanged(Editable editable){

    }
}
