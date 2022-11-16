package com.example.poi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.example.poi.managers.ConfigManager;
import com.example.poi.managers.DBManager;
import com.example.poi.utils.DBEvent;
import com.example.poi.utils.EventViewAdapter;
import com.example.poi.managers.LayoutManager;
import com.example.poi.utils.MapEvents;
import com.example.poi.utils.NewEventDialog;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ConfigManager configManager;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dbManager = new DBManager(this);

        LayoutManager layoutManager = new LayoutManager(this);
        layoutManager.registerListener();

        ListView listView = findViewById(R.id.eventViewList);
        listView.setAdapter(new EventViewAdapter(this.getDBManager(), this));

        this.configManager = new ConfigManager(this);

        try {
            this.configManager.loadConfig();
        } catch (IOException | JSONException e) {
            Toast.makeText(this, "Nepodařilo se načíst nastavení.", Toast.LENGTH_SHORT).show();
        }
    }

    public void settingChanged(View view) {
        try {
            this.configManager.saveConfig();
        } catch (IOException | JSONException e) {
            Toast.makeText(this, "Nepodařilo se uložit nastavení.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addEvent(View view) {
        Intent intent = getMapIntent();
        intent.putExtra("initMode", MapEvents.ADD_MODE);
        this.startActivity(intent);
    }

    public void openMap(View v) {
        this.startActivity(getMapIntent());
    }

    public Intent getMapIntent() {
        Intent mapIntent = new Intent(this, MapActivity.class);
        JSONObject config = this.configManager.getConfig();
        mapIntent.putExtra("configSettings", config != null ? config.toString() : "{}");
        return mapIntent;
    }
    
    public DBManager getDBManager() {
        return this.dbManager;
    }
}