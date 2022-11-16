package com.example.poi.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.poi.MainActivity;
import com.example.poi.R;
import com.example.poi.managers.DBManager;

public class EventViewAdapter extends BaseAdapter implements ListAdapter {
    private final DBManager manager;
    private final MainActivity activity;

    public EventViewAdapter(DBManager manager, MainActivity activity) {
        this.manager = manager;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.manager.getEventList().size();
    }

    @Override
    public DBEvent getItem(int pos) {
        return this.manager.getEventList().get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @SuppressLint({"DefaultLocale", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.event_view, null);
        }

        DBEvent event = this.manager.getEventList().get(position);
        ((TextView)view.findViewById(R.id.eventTitle)).setText(event.getTitle());
        ((TextView)view.findViewById(R.id.eventDateTime)).setText(event.getDateTimeFormatted());
        ((TextView)view.findViewById(R.id.eventGPS)).setText(String.format("%.5f;%.5f", event.getLatitude(), event.getLatitude()));

        view.findViewById(R.id.showOnMapButton).setOnClickListener(v -> {
            Intent intent = activity.getMapIntent();
            intent.putExtra("initZoomLatitude", Double.toString(event.getLatitude()));
            intent.putExtra("initZoomLongitude", Double.toString(event.getLongitude()));
            activity.startActivity(intent);
        });
        view.findViewById(R.id.editEventButton).setOnClickListener(v -> {});
        view.findViewById(R.id.deleteEventButton).setOnClickListener(v -> askForConfirmation(v, event));

        return view;
    }

    private void askForConfirmation(View view, DBEvent event) {
        new AlertDialog.Builder(activity)
                .setTitle(((TextView)((RelativeLayout) view.getParent()).findViewById(R.id.eventTitle)).getText())
                .setMessage("Opravdu chceš smazat tuto událost?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    manager.removeEvent(event);
                    notifyDataSetChanged();
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}