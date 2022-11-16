package com.example.poi.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import com.example.poi.utils.DBEvent;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBManager {
    public static final String DATABASE_NAME = "NightDrive.db";

    private ArrayList<DBEvent> list = new ArrayList<>();
    private final SQLiteDatabase sqLiteDatabase;

    public DBManager(AppCompatActivity activity) {
        this.sqLiteDatabase = activity.openOrCreateDatabase(DBManager.DATABASE_NAME, Context.MODE_PRIVATE, null);
        this.sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS events(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, datetime TEXT NOT NULL, latitude REAL, longitude REAL, track TEXT);"
        );

        this.loadEventsFromDB();
    }

    @SuppressLint("Recycle")
    private void loadEventsFromDB() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM events",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DBEvent event = new DBEvent(
                    Integer.parseInt(getColumnValue(cursor, "id")),
                    getColumnValue(cursor, "title"),
                    getColumnValue(cursor, "description")
            );
            event.setDateTime(LocalDateTime.parse(getColumnValue(cursor, "datetime")));
            event.setLatitude(Double.parseDouble(getColumnValue(cursor,"latitude")));
            event.setLongitude(Double.parseDouble(getColumnValue(cursor,"longitude")));
            try {
                event.setTrack(new JSONObject(getColumnValue(cursor, "track")));
            } catch (Exception e) {
                // TODO: log
            }

            this.list.add(event);
            cursor.moveToNext();
        }
    }

    private String getColumnValue(Cursor cursor, String column) {
        return cursor.getString((int) cursor.getColumnIndex(column));
    }

    public void removeEvent(DBEvent event) {
        this.sqLiteDatabase.execSQL("DELETE FROM events WHERE id =" + event.getId());
        this.list.remove(event);
    }

    public void addEvent(DBEvent event) {
        this.sqLiteDatabase.execSQL("INSERT INTO events (title, description, datetime, latitude, longitude, track) VALUES(" +
                "'" + event.getTitle() + "'," +
                "'" + event.getDescription() + "'," +
                "'" + LocalDateTime.now().toString() + "'," +
                "" + event.getLatitude() + "," +
                "" + event.getLongitude() + "," +
                "'" + event.getTrack() + "');");

        this.list.add(event);
    }

    public ArrayList<DBEvent> getEventList() {
        return this.list;
    }
}
