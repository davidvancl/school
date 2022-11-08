package com.example.cv03;

import android.view.MotionEvent;
import android.view.View;

public class MyTouchListener implements View.OnTouchListener {
    private final MainActivity activity;

    public MyTouchListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float variable_x = v.getX() + event.getX() - ((float) this.activity.icon.getWidth() / 2);
            float variable_y = v.getY() + event.getY() - ((float) this.activity.icon.getHeight() / 2);

            this.activity.icon.setX(variable_x);
            this.activity.icon.setY(variable_y);
        }
        return false;
    }
}
