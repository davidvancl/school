package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_manager);
        try {
            this.processExtras(getIntent());
        } catch (JSONException e) {
            Log.e("JSONParse", e.toString());
        }
    }

    @SuppressLint("SetTextI18n")
    private void processExtras(Intent localIntent) throws JSONException {
        if (localIntent.hasExtra("BMI")) {
            JSONObject dataObject = new JSONObject(localIntent.getStringExtra("BMI"));

            double height = Double.parseDouble(dataObject.get("height").toString()) / 100;
            double weight = Double.parseDouble(dataObject.get("weight").toString());
            double bmi = weight / (height * height);

            String stringResult = "";
            ImageView imageView = findViewById(R.id.imageOutput);
            if (bmi >= 40) {
                stringResult = "obezita třetího stupně";
                imageView.setBackgroundResource(R.drawable.result_5);
            } else if (bmi >= 35) {
                stringResult = "obezita druhého stupně";
                imageView.setBackgroundResource(R.drawable.result_4);
            } else if (bmi >= 30) {
                stringResult = "obezita prvního stupně";
                imageView.setBackgroundResource(R.drawable.result_3);
            } else if (bmi >= 25) {
                stringResult = "nadváha";
                imageView.setBackgroundResource(R.drawable.result_2);
            } else if (bmi >= 18.5) {
                stringResult = "optimální váha";
                imageView.setBackgroundResource(R.drawable.result_1);
            } else {
                stringResult = "podváha";
                imageView.setBackgroundResource(R.drawable.result_0);
            }

            ((TextView) findViewById(R.id.output)).setText("BMI = " + Math.round(bmi * 100.0) / 100.0 + " -> " + stringResult);
        } else if (localIntent.hasExtra("FUEL")) {

        }
    }

    public void doSwitch(View v) {
        Intent intent;
        if (v.getId() == R.id.bmi2) {
            intent = new Intent(getApplicationContext(), BMICounter.class);
        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

        startActivity(intent);
    }
}