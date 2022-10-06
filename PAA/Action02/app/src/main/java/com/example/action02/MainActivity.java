package com.example.action02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v)
    {
        EditText inputObject = findViewById(R.id.input);
        TextView outputObject = findViewById(R.id.output);

        outputObject.setText(inputObject.getText().toString());
        inputObject.setText("");
        setContentView(R.layout.activity_main);
    }

    public void onSwitch(View v)
    {
        Intent switchActivityIntent = new Intent(this, Activity02.class);
        startActivity(switchActivityIntent);
    }
}