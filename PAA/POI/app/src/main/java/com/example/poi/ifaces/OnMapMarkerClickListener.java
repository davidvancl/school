package com.example.poi.ifaces;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public interface OnMapMarkerClickListener extends Marker.OnMarkerClickListener {
    abstract boolean onMarkerClick(Marker marker, MapView mapView);
    abstract boolean onLongPress(Marker marker, MapView mapView);
}
