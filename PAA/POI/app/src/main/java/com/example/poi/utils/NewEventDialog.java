package com.example.poi.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.time.LocalDateTime;

public class NewEventDialog {
    private final Context context;

    public NewEventDialog(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    public void getEvent(AddEventListener callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Přidat událost");

        LinearLayout layout = new LinearLayout(this.context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView titleLabel = new TextView(this.context);
        titleLabel.setText("Název události:");

        EditText titleInput = new EditText(this.context);
        titleInput.setInputType(InputType.TYPE_CLASS_TEXT);

        TextView descriptionLabel = new TextView(this.context);
        descriptionLabel.setText("Popis události:");

        EditText descriptionInput = new EditText(this.context);
        titleInput.setInputType(InputType.TYPE_CLASS_TEXT);

        layout.addView(titleLabel);
        layout.addView(titleInput);
        layout.addView(descriptionLabel);
        layout.addView(descriptionInput);

        builder.setView(layout);

        builder.setPositiveButton("OK", (dialog, which) -> {

            DBEvent event = new DBEvent(-1, titleInput.getText().toString(), descriptionInput.getText().toString());
            event.setDateTime(LocalDateTime.now());

            callback.callback(event);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
