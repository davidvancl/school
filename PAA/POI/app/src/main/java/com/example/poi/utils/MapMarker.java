package com.example.poi.utils;

import android.view.MotionEvent;
import com.example.poi.ifaces.OnMapMarkerClickListener;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapMarker extends Marker {
    protected OnMapMarkerClickListener mapMarkerClickListener;

    public MapMarker(MapView mapView) {
        super(mapView);
    }

    public void setOnMapMarkerClickListener(OnMapMarkerClickListener listener) {
        mapMarkerClickListener = listener;
    }

    @Override
    public boolean onSingleTapConfirmed(final MotionEvent event, final MapView mapView) {
        boolean touched = hitTest(event, mapView);
        if (touched) {
            return mapMarkerClickListener.onMarkerClick(this, mapView);
        } else
            return false;
    }

    @Override
    public boolean onLongPress(final MotionEvent event, final MapView mapView) {
        return mapMarkerClickListener.onLongPress(this, mapView);
    }
}
