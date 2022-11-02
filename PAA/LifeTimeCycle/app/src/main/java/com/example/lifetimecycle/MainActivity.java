package com.example.lifetimecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            Log.v("onCreate", "savedInstanceState neni prazdne");
        } else {
            Log.v("onCreate", "savedInstanceState je prazdne");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        this.clickCounter = sharedPreferences.getInt("COUNT_CLICKER", 0);
    }

    public void click(View v) {
        this.clickCounter++;

        this.refreshMainText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("onPause", "Je pauznuty");
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("COUNT_CLICKER", this.clickCounter);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CLICK_COUNTER", this.clickCounter);
        super.onSaveInstanceState(outState);
        Log.v("onCreate", "Ukladam pocet kliknuti.");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.clickCounter = savedInstanceState.getInt("CLICK_COUNTER", 0);
        Log.v("onCreate", "Nacitam pocet kliknuti.");
    }

    private void refreshMainText() {
        TextView textView = findViewById(R.id.textView);
        textView.setText("Kliknuto: " + this.clickCounter);
    }
}