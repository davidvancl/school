package com.example.poi.utils;

import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBEvent {
    private final int id;
    private final String title;
    private final String description;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
    private JSONObject track = new JSONObject();
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;

    public DBEvent(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setTrack(JSONObject track){
        this.track = track;
    }

    public String getTrack() {
        return this.track.toString();
    }

    public int getId() {
        return this.id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTimeFormatted() {
        return this.dateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
