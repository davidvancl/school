package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView output;
    private String lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.output = findViewById(R.id.output);
    }

    public void doAction(View view) {
        try {
            String actionValue = ((Button) findViewById(view.getId())).getText().toString();
            switch (actionValue) {
                case "AC":
                    this.doReset();
                    break;
                case "=":
                    this.doEvaluate();
                    break;
                case "ANS":
                    this.doCopy();
                    break;
                default:
                    this.addValue(actionValue);
            }
        } catch (Exception e) {
            Log.e("activityLog",e.toString());
            this.doReset();
        }
    }

    private void doCopy() {
        this.addValue(this.lastResult);
    }

    private void doEvaluate() throws Exception {
        String[] numbersAndOperations = this.output.getText().toString().split("(?<=[-+*/(),])(?=.)|(?<=.)(?=[-+*/(),])");

        Double number = null;
        String operation = null;
        boolean expectNumber = true;

        for (String action : numbersAndOperations) {
            if (expectNumber && !this.isNumber(action)) {
                throw new Exception("Invalid equation");
            }

            if (this.isNumber(action)) {
                if (number != null && operation != null) {
                    switch (operation) {
                        case "/":
                            number /= Double.parseDouble(action);
                            break;
                        case "*":
                            number *= Double.parseDouble(action);
                            break;
                        case "-":
                            number -= Double.parseDouble(action);
                            break;
                        case "+":
                        default:
                            number += Double.parseDouble(action);
                            break;
                    }
                } else {
                    number = Double.parseDouble(action);
                }
            } else {
                operation = action;
            }

            expectNumber = !expectNumber;
        }

        String result = (number == null ? "0" : number).toString();
        this.output.setText(result);
        this.lastResult = result;
    }

    private void doReset() {
        this.output.setText("");
    }
    
    private void addValue(String value) {
        this.output.append(value);
    }

    private boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void doSwitch(View v) {
        Intent intent = new Intent(getApplicationContext(), BMICounter.class);
        startActivity(intent);
    }
}