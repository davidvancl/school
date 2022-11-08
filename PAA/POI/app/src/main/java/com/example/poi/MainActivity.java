package com.example.poi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.poi.utils.ConfigManager;
import com.example.poi.utils.LayoutManager;
import org.json.JSONException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ConfigManager configManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutManager layoutManager = new LayoutManager(this);
        layoutManager.registerListener();

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

    public void openMap(View v) {
        Intent mapIntent = new Intent(this, MapActivity.class);
        mapIntent.putExtra("configSettings", this.configManager.getConfig().toString());
        this.startActivity(mapIntent);
    }
}