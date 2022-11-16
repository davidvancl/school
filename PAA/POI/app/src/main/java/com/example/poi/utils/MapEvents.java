package com.example.poi.utils;

import com.example.poi.workers.MapWorker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public final class MapEvents {
    public static String VIEW_MODE = "setView";
    public static String DELETE_MODE = "setDelete";
    public static String ADD_MODE = "setAdd";

    private MapEvents() {

    }

    public static void executeViewEvent(Marker marker, MapView mapView, boolean open) {

    }

    public static void executeAddEvent(GeoPoint geoPoint, MapWorker mapWorker, DBEvent event) {
        mapWorker.addMarker(geoPoint, event);
    }

    public static void executeDeleteEvent(Marker marker, MapWorker mapWorker) {
        mapWorker.getMap().getOverlays().remove(marker);
        mapWorker.getMap().invalidate();
    }
}
