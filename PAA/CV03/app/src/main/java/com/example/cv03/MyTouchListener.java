package com.example.cv03;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MyTouchListener implements View.OnTouchListener {
    private MainActivity activity;

    public MyTouchListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            this.activity.icon.setX(v.getX() + event.getX());
            this.activity.icon.setY(v.getY() + event.getY());
        }
        return false;
    }
}
