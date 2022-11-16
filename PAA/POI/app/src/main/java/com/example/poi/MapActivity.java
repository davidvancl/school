package com.example.poi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.poi.workers.MapWorker;
import com.example.poi.managers.PermissionManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MapActivity extends AppCompatActivity {
    private final String PER_PREF = "SERIALIZED_PERMISSIONS";
    private final String DEF_PREF = "DEFAULT_PREFERENCES";

    private PermissionManager permissionManager;
    private MapWorker mapWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        this.permissionManager = new PermissionManager(getSharedPreferences(this.PER_PREF, MODE_PRIVATE), this);

        // Load config map
        if (getIntent().getExtras() != null) {
            JSONObject mapSettings = new JSONObject();
            try {
                mapSettings = new JSONObject(Objects.requireNonNull(getIntent().getExtras().getString("configSettings")));
            } catch (JSONException e) {
                e.printStackTrace();
                // TODO: log
            }

            // Setup map
            this.mapWorker = new MapWorker(this, getSharedPreferences(this.DEF_PREF, MODE_PRIVATE));
            this.mapWorker.setupContext();
            this.mapWorker.loadMap();
            this.mapWorker.addMyLocation();
            this.mapWorker.registerEventOverlay();
            this.mapWorker.setZoom( 19, 50.773388, 15.075062);

            // Apply additional features
            if (this.validateSettingByName(mapSettings,"showCompas")) {
                this.mapWorker.addCompass();
            }

            if (this.validateSettingByName(mapSettings,"showMapLines")) {
                this.mapWorker.addNavigationLines();
            }
            if (this.validateSettingByName(mapSettings,"showScaleBar")) {
                this.mapWorker.addScaleBar();
            }

            if (this.validateSettingByName(mapSettings,"showMiniMap")) {
                this.mapWorker.addMiniMap();
            }

            this.mapWorker.renderTrack();
            this.mapWorker.addMarker(50.773388, 15.075062);
        }
    }

    private boolean validateSettingByName(JSONObject settings, String value) {
        try {
            return (settings.has(value) && (boolean) settings.get(value));
        } catch (JSONException e) {
            return false;
        }
    }

    public void onResume(){
        super.onResume();
        this.mapWorker.doMapResume();
    }

    public void onPause(){
        super.onPause();
        this.mapWorker.doMapPause();
    }

    public void openModPanel(View view) {
        ConstraintLayout layout = findViewById(R.id.modPanel);
        layout.setVisibility((layout.getVisibility() == View.VISIBLE) ? View.INVISIBLE : View.VISIBLE);
    }

    public void changeMode(View view) {
        this.mapWorker.setEventMode(this.getResources().getResourceEntryName(view.getId()));
        Button modeButton = findViewById(R.id.changeModeButton);
        modeButton.setBackground(view.getBackground().getCurrent());
        ((ConstraintLayout) findViewById(R.id.modPanel)).setVisibility(View.INVISIBLE);
    }

    public void doReturnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}