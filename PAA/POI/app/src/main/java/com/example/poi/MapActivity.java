package com.example.poi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.poi.utils.MapWorker;
import com.example.poi.utils.PermissionManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;
import java.util.ArrayList;

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

        // Setup map
        this.mapWorker = new MapWorker(this, getSharedPreferences(this.DEF_PREF, MODE_PRIVATE));
        this.mapWorker.setupContext();
        this.mapWorker.getMap();

        // Apply additional features
        this.mapWorker.addZoom( 19, 50.773388, 15.075062);
        this.mapWorker.addCompass();
        this.mapWorker.addMyLocation();
        if (false) {
            this.mapWorker.addNavigationLines();
        }
        this.mapWorker.addScaleBar();
        this.mapWorker.addMarker(50.773388, 15.075062);
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