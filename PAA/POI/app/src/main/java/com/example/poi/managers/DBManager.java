package com.example.poi.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import com.example.poi.utils.DBEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBManager {
    public static final String DATABASE_NAME = "NightDrive.db";

    private ArrayList<DBEvent> list = new ArrayList<>();
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(AppCompatActivity activity) {
        this.sqLiteDatabase = activity.openOrCreateDatabase(DBManager.DATABASE_NAME, Context.MODE_PRIVATE, null);
        this.sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS events(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, datetime TEXT, latitude REAL, longitude REAL, track TEXT);"
        );

        this.loadEventsFromDB();
    }

    @SuppressLint("Recycle")
    private void loadEventsFromDB() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM events",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cursor.getString(0);
            DBEvent event = new DBEvent(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2)
            );

            //TODO: add params
            this.list.add(event);

            cursor.moveToNext();
        }
    }

    public void removeEvent(DBEvent event) {
        this.list.remove(event);
    }

    public void addEvent(DBEvent event) {
        // TODO: padá insert fix
        this.sqLiteDatabase.execSQL("INSERT INTO events VALUES(0," +
                "" + event.getTitle() + "," +
                "" + event.getDescription() + "," +
                "" + LocalDateTime.now() + "," +
                "" + event.getLatitude() + "," +
                "" + event.getLongitude() + "," +
                "" + event.getTrack() + ");");

        this.list.add(event);
    }

    public ArrayList<DBEvent> getEventList() {
        return this.list;
    }
}
