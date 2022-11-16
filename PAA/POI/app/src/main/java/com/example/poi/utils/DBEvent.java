package com.example.poi.utils;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBEvent {
    private int id;
    private String title;
    private String description;

    private JSONObject track = new JSONObject();
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;

    public DBEvent(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrack(JSONObject track){
        this.track = track;
    }

    public String getTrack() {
        return this.track.toString();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
