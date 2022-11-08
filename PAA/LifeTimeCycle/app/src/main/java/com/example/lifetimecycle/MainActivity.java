package com.example.lifetimecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int restartCounter = 0;
    private int clickCounter = 0;
    private int previousClicks = 0;

    private long lastDown;
    private long keyPressedDuration;

    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sqLiteDatabase = openOrCreateDatabase("clicks",MODE_PRIVATE,null);
        this.sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS clickedPoints(allClicks INT,nowClick INT, holdTime INT, appReset INT);");

        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        this.clickCounter = sharedPreferences.getInt("COUNT_CLICKER", 0);
        this.restartCounter = sharedPreferences.getInt("COUNT_RESTART", 0);
        this.previousClicks = this.clickCounter;

        this.setTouchListener();

        this.loadHistory();
    }

    private ArrayList<String[]> loadHistory() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM clickedPoints",null);

        ArrayList<String[]> history = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cursor.getString(0);

            history.add(new String[]{
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
            });

//            textView.append(
//                    "Celkem: " + cursor.getString(0) + "\n" +
//                    "Nyní: " + cursor.getString(1) + "\n" +
//                    "Drženo: " + cursor.getString(2) + "ms\n" +
//                    "Restartováno: " + cursor.getString(3) + "\n\n"
//            );

            cursor.moveToNext();
        }

        return history;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListener() {
        ((Button) findViewById(R.id.button)).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                lastDown = System.currentTimeMillis();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                clickCounter++;
                keyPressedDuration = System.currentTimeMillis() - lastDown;

                saveAndRefresh(
                        this.clickCounter,
                        this.clickCounter - this.previousClicks,
                        (int) this.keyPressedDuration,
                        this.restartCounter
                );
            }
            return true;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("onPause", "Je pauznuty");
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("COUNT_CLICKER", this.clickCounter);
        editor.putInt("COUNT_RESTART", this.restartCounter);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CLICK_COUNTER", this.clickCounter);
        outState.putInt("COUNT_RESTART", ++this.restartCounter);
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
        this.restartCounter = savedInstanceState.getInt("COUNT_RESTART", 0);
        Log.v("onCreate", "Nacitam pocet kliknuti.");
    }

    @SuppressLint("SetTextI18n")
    private void saveAndRefresh(int cc, int pc, int kpd, int rc) {
        this.sqLiteDatabase.execSQL("INSERT INTO clickedPoints VALUES(" + cc + "," + pc + ", " + kpd + ", " + rc + ");");

        TextView textView = findViewById(R.id.textView);
        textView.setText(
            "Celkem: " + cc + "\n" +
            "Nyní: " + pc + "\n" +
            "Drženo: " + kpd + "ms\n" +
            "Restartováno: " + rc
        );
    }
}