package com.example.cv03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
public class MainActivity extends AppCompatActivity {
    public ImageButton icon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.icon = findViewById(R.id.imageButton);
        this.icon.setOnTouchListener(new MyTouchListener(this));
    }
}