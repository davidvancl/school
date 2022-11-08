package com.example.poi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

public class ConfigManager {
    private final AppCompatActivity activity;
    private final String CONFIG_NAME = "config.txt";
    private final String[] settingNames = {"showCompas", "showScaleBar", "showMapLines", "showMiniMap"};

    private final ArrayList<Object> activitySettings = new ArrayList<>();
    private JSONObject settingObject;

    public ConfigManager(AppCompatActivity activity) {
        this.activity = activity;
    }

    @SuppressLint("DiscouragedApi")
    private ArrayList<Object> loadSettings() {
        if (this.activitySettings.isEmpty()) {
            for (String idValue : this.settingNames) {
                this.activitySettings.add(
                    this.activity.findViewById(
                        this.activity.getResources().getIdentifier(idValue, "id", this.activity.getPackageName())
                    )
                );
            }
        }

        return this.activitySettings;
    }

    private String getSerializedData() throws JSONException {
        this.settingObject = new JSONObject();

        for (Object settingObject : this.loadSettings()) {
            if (settingObject instanceof Switch) {
                this.settingObject.put(
                    this.activity.getResources().getResourceEntryName(((Switch) settingObject).getId()),
                    ((Switch) settingObject).isChecked()
                );
            }
        }

        return this.settingObject.toString();
    }

    private String loadConfigData() throws IOException {
        InputStream inputStream = this.activity.openFileInput(this.CONFIG_NAME);
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            return stringBuilder.toString();
        }

        return null;
    }

    public void saveConfig() throws IOException, JSONException {
        OutputStreamWriter osw = new OutputStreamWriter(
                this.activity.openFileOutput(this.CONFIG_NAME, Context.MODE_PRIVATE)
        );
        osw.write(this.getSerializedData());
        osw.close();
    }

    public void loadConfig() throws IOException, JSONException {
        this.settingObject = new JSONObject(Objects.requireNonNull(this.loadConfigData()));

        for (Object settingObject : this.loadSettings()) {
            if (settingObject instanceof Switch) {
                String name = this.activity.getResources().getResourceEntryName(((Switch) settingObject).getId());
                ((Switch) settingObject).setChecked((boolean) this.settingObject.get(name));
            }
        }
    }

    public JSONObject getConfig() {
        return this.settingObject;
    }
}
