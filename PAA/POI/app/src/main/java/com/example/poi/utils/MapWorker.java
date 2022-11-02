package com.example.poi.utils;

import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import androidx.appcompat.app.AppCompatActivity;
import com.example.poi.R;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.util.ArrayList;

public class MapWorker {
    private final AppCompatActivity activity;
    private final SharedPreferences sharedPreferences;
    private MapView map = null;

    public MapWorker(AppCompatActivity activity, SharedPreferences sharedPreferences) {
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
    }

    public void setupContext() {
        Configuration.getInstance().load(this.activity.getApplicationContext(), this.sharedPreferences);
        this.activity.setContentView(R.layout.activity_map);
    }

    public void getMap() {
        this.map = this.activity.findViewById(R.id.map);
        this.map.setTileSource(TileSourceFactory.MAPNIK);
        (new CustomZoomButtonsController(this.map)).setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        this.map.setMultiTouchControls(true);
    }

    public void addZoom(double zoom, double x, double y) {
        IMapController mapController = this.map.getController();
        mapController.setZoom(zoom);
        GeoPoint startPoint = new GeoPoint(x, y);
        mapController.setCenter(startPoint);
    }

    public void addCompass() {
        CompassOverlay mCompassOverlay = new CompassOverlay(this.activity, new InternalCompassOrientationProvider(this.activity), this.map);
        mCompassOverlay.enableCompass();
        this.map.getOverlays().add(mCompassOverlay);
    }

    public void addMyLocation() {
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this.activity), this.map);
        mLocationOverlay.enableMyLocation();
        this.map.getOverlays().add(mLocationOverlay);
    }

    public void addNavigationLines() {
        LatLonGridlineOverlay2 overlay = new LatLonGridlineOverlay2();
        this.map.getOverlays().add(overlay);
    }

    public void addScaleBar() {
        DisplayMetrics dm = this.activity.getResources().getDisplayMetrics();
        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(this.map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        this.map.getOverlays().add(mScaleBarOverlay);
    }

    public void addMarker(double x, double y) {
        GeoPoint startPoint = new GeoPoint(x, y);
        Marker marker = new Marker(this.map);
        marker.setPosition(startPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                return true;
            }
        });
        this.map.getOverlays().add(marker);
    }

    public void doMapPause() {
        this.map.onPause();
    }

    public void doMapResume() {
        this.map.onResume();
    }
}
