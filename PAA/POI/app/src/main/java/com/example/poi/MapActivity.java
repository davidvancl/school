package com.example.poi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.poi.ifaces.MapEvent;
import com.example.poi.utils.MapWorker;
import com.example.poi.utils.PermissionManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

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
        Bundle extras = getIntent().getExtras();
        JSONObject mapSettings;
        if (extras != null) {
            try {
                mapSettings = new JSONObject(Objects.requireNonNull(extras.getString("configSettings")));

                // Setup map
                this.mapWorker = new MapWorker(this, getSharedPreferences(this.DEF_PREF, MODE_PRIVATE));
                this.mapWorker.setupContext();
                this.mapWorker.loadMap();
                this.mapWorker.addMyLocation();
                this.mapWorker.registerEventOverlay(this.loadMapEvents());
                this.mapWorker.setZoom( 19, 50.773388, 15.075062);

                // Apply additional features
                if ((boolean) mapSettings.get("showCompas")) {
                    this.mapWorker.addCompass();
                }

                if ((boolean) mapSettings.get("showMapLines")) {
                    this.mapWorker.addNavigationLines();
                }
                if ((boolean) mapSettings.get("showScaleBar")) {
                    this.mapWorker.addScaleBar();
                }

                if ((boolean) mapSettings.get("showMiniMap")) {
                    this.mapWorker.addMiniMap();
                }

                this.mapWorker.renderTrack();

        this.mapWorker.addMarker(50.773388, 15.075062);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private MapEvent loadMapEvents() {
        return new MapEvent() {
            @Override
            public void singleTapEvent(GeoPoint geoPoint) {
                mapWorker.addMarker(geoPoint);
            }

            @Override
            public void longPressEvent(GeoPoint geoPoint) {

            }
        };
    }

    public void onResume(){
        super.onResume();
        this.mapWorker.doMapResume();
    }

    public void onPause(){
        super.onPause();
        this.mapWorker.doMapPause();
    }

    public void doReturnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}