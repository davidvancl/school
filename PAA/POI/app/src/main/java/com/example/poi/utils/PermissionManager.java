package com.example.poi.utils;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private final SharedPreferences sharedPreferences;
    private final AppCompatActivity activity;
    private SharedPreferences.Editor editor;

    public PermissionManager(SharedPreferences sharedPreferences, AppCompatActivity activity) {
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
    }

    private void setPermission(String permission, boolean isFirstTime) {
        this.doEdit();
        this.editor.putBoolean(permission, isFirstTime);
        this.doCommit();
    }

    private boolean hasPreviousPermission(String permission) {
        return this.sharedPreferences.getBoolean(permission, true);
    }

    private void doEdit() {
        if (this.editor == null) {
            this.editor = this.sharedPreferences.edit();
        }
    }

    private void doCommit() {
        if (this.editor != null) {
            this.editor.commit();
            this.editor = null;
        }
    }

    public boolean checkMapPermissions() {
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, Manifest.permission.INTERNET)) {
                this.showExplanation("Oprávnění", "Následující oprávnění se vstahují na správné zobrazení mapy.");
                return true;
            } else {
                if (this.hasPreviousPermission(Manifest.permission.INTERNET)) {
                    this.setPermission(Manifest.permission.INTERNET, false);
                    this.requestPermission();
                } else {
                    Toast.makeText(this.activity, "Nejsou povolena potřebná oprávnění.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        } else {
            Toast.makeText(this.activity, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void showExplanation(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> requestPermission());
        builder.create().show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this.activity, new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        }, 1);
    }
}
