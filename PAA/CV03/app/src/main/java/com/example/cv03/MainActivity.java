package com.example.cv03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public ConstraintLayout window = null;
    public ImageButton icon = null;
    public TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.window = findViewById(R.id.main_window);
        this.icon = findViewById(R.id.imageButton);
        this.icon.setOnTouchListener(new MyTouchListener(this));
        this.textView = findViewById(R.id.output);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.updateSizeInfo();
        this.moveIcon();
    }

    @SuppressLint("SetTextI18n")
    private void updateSizeInfo() {
        this.textView.setText("Rozlišení: " + window.getWidth() + " x " + window.getHeight());
    }

    private void moveIcon() {
        this.icon.setX(this.generateNumber(this.window.getWidth() - this.icon.getWidth()));
        this.icon.setY(this.generateNumber(this.window.getHeight() - icon.getHeight()));

        this.window.requestLayout();
    }

    private int generateNumber(int Max) {
        return new Random().nextInt(Max + 1);
    }
}