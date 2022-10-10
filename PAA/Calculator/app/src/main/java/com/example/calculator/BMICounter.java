package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Objects;

public class BMICounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicounter);

        ((SeekBar) findViewById(R.id.height)).setOnSeekBarChangeListener(this.loadListener("cm"));
        ((SeekBar) findViewById(R.id.weight)).setOnSeekBarChangeListener(this.loadListener("kg"));
    }

    public void doResult(View view) throws JSONException {
        Intent intent = new Intent(getApplicationContext(), ResultManager.class);

        JSONObject dataObject = new JSONObject();
        dataObject.put("weight", ((SeekBar) findViewById(R.id.weight)).getProgress());
        dataObject.put("height", ((SeekBar) findViewById(R.id.height)).getProgress());

        intent.putExtra("BMI", dataObject.toString());
        startActivity(intent);
    }

    public void openCalc(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private SeekBar.OnSeekBarChangeListener loadListener(String suffix) {
        return new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                ((TextView) findViewById(
                        Objects.equals(suffix, "cm") ? R.id.height_value : R.id.weight_value)
                ).setText(progress + " " + suffix);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }
}