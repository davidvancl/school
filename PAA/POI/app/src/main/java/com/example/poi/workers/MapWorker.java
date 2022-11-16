package com.example.poi.workers;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.poi.R;
import com.example.poi.utils.MapEvents;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions;
import org.osmdroid.views.overlay.simplefastpoint.SimplePointTheme;

import java.util.ArrayList;
import java.util.List;

public class MapWorker {
    private String eventMode = MapEvents.VIEW_MODE;
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

    public void loadMap() {
        if (this.map == null) {
            this.map = this.activity.findViewById(R.id.map);
        }
        this.map.setTileSource(TileSourceFactory.MAPNIK);
        (new CustomZoomButtonsController(this.map)).setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT);
        this.map.setMultiTouchControls(true);
        this.map.getOverlays().clear();
    }

    public void setZoom(double zoom, double x, double y) {
        this.map.getController().setZoom(zoom);
        this.map.getController().setCenter(new GeoPoint(x, y));
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
        this.map.getOverlays().add(new LatLonGridlineOverlay2());
    }

    public void addScaleBar() {
        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(this.map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(this.activity.getResources().getDisplayMetrics().widthPixels / 2, 10);
        this.map.getOverlays().add(mScaleBarOverlay);
    }

    public void registerEventOverlay() {
        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint geoPoint) {
                if (eventMode.equals(MapEvents.ADD_MODE)) {
                    MapEvents.executeAddEvent(geoPoint, getMapWorker());
                }
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };
        this.map.getOverlays().add(new MapEventsOverlay(mapEventsReceiver));
    }

    public void addMiniMap() {
        MinimapOverlay mMinimapOverlay = new MinimapOverlay(this.map.getContext(), this.map.getTileRequestCompleteHandler());
        mMinimapOverlay.setWidth(this.map.getResources().getDisplayMetrics().widthPixels / 4);
        mMinimapOverlay.setHeight(this.map.getResources().getDisplayMetrics().heightPixels / 5);
        mMinimapOverlay.setZoomDifference(5);
        this.map.getOverlays().add(mMinimapOverlay);
    }

    public void renderTrack() {
        List<IGeoPoint> points = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            points.add(new LabelledGeoPoint(37 + Math.random() * 5, -8 + Math.random() * 5
                    , "Point #" + i));
        }

        // wrap them in a theme
        SimplePointTheme pt = new SimplePointTheme(points, true);

        Paint textStyle = new Paint();
        textStyle.setStyle(Paint.Style.FILL);
        textStyle.setColor(Color.parseColor("#0000ff"));
        textStyle.setTextAlign(Paint.Align.CENTER);
        textStyle.setTextSize(24);

        SimpleFastPointOverlayOptions opt = SimpleFastPointOverlayOptions.getDefaultStyle()
                .setAlgorithm(SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION)
                .setRadius(7).setIsClickable(true).setCellSize(30).setTextStyle(textStyle);

        final SimpleFastPointOverlay sfpo = new SimpleFastPointOverlay(pt, opt);

        // onClick callback
        sfpo.setOnClickListener((points1, point) -> Toast.makeText(map.getContext()
                , "You clicked " + ((LabelledGeoPoint) points1.get(point)).getLabel()
                , Toast.LENGTH_SHORT).show());

        // add overlay
        this.map.getOverlays().add(sfpo);
    }

    public void addMarker(double x, double y) {
        this.addMarker(new GeoPoint(x, y));
    }

    public void addMarker(GeoPoint geoPoint) {
        Marker marker = new Marker(this.map);
        marker.setIcon(ContextCompat.getDrawable(this.activity, R.drawable.marker_meet));
        marker.setPosition(geoPoint);

        marker.setTitle("Marker");
        marker.setSnippet("Snippet marker");
        marker.setSubDescription("SubDescription marker");

        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setOnMarkerClickListener((marker1, mapView) -> {
            if (eventMode.equals(MapEvents.VIEW_MODE)) {
                if (marker1.isInfoWindowShown()) {
                    marker1.closeInfoWindow();
                    MapEvents.executeViewEvent(marker1, mapView, false);
                } else {
                    marker1.showInfoWindow();
                    MapEvents.executeViewEvent(marker1, mapView, true);
                }
            }

            if (eventMode.equals(MapEvents.DELETE_MODE)) {
                MapEvents.executeDeleteEvent(marker1, getMapWorker());
            }

            return true;
        });

        this.map.getOverlays().add(marker);
        map.invalidate();
    }

    public MapWorker getMapWorker() {
        return this;
    }

    public MapView getMap() {
        return this.map;
    }

    public AppCompatActivity getActivity() {
        return this.activity;
    }

    public void setEventMode(String eventMode) {
        this.eventMode = eventMode;
    }

    public void doMapPause() {
        this.map.onPause();
    }

    public void doMapResume() {
        this.map.onResume();
    }
}
