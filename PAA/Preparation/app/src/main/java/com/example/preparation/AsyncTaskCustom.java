package com.example.preparation;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class AsyncTaskCustom extends AsyncTask<String, String, Bitmap> {
    private int number = 0;
    private int cycles = 0;
    private final TextView outout;

    public AsyncTaskCustom(TextView output) {
        this.outout = output;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        for (int i = 0; i <= this.getRandomNumber(); i++) {
            cycles++;
            number += this.getRandomNumber();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        this.outout.setText("C: " + this.cycles + " Num: " + this.number);
    }

    private int getRandomNumber() {
        final int min = 10;
        final int max = 50;
        return new Random().nextInt((max - min) + 1) + min;
    }
}
