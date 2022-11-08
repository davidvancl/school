package com.example.poi.ifaces;

import org.osmdroid.util.GeoPoint;

public interface MapEvent {
    public void singleTapEvent(GeoPoint geoPoint);
    public void longPressEvent(GeoPoint geoPoint);
}
