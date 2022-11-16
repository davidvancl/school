package com.example.poi.managers;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.poi.R;
import com.google.android.material.tabs.TabLayout;

public class LayoutManager {
    private final int GENERAL = 0;
    private final int MY_POINTS = 1;
    private final int SETTINGS = 2;

    private final TabLayout tabLayout;
    private final AppCompatActivity activity;
    public LayoutManager(AppCompatActivity activity){
        this.tabLayout = activity.findViewById(R.id.tabLayout);
        this.activity = activity;
    }

    public void registerListener() {
        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                this.renderLayout(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

            private void renderLayout(int position) {
                ConstraintLayout settingFrame = activity.findViewById(R.id.settingFrame);
                ConstraintLayout generalFrame = activity.findViewById(R.id.generalFrame);
                ConstraintLayout myPointsFrame = activity.findViewById(R.id.myPointsFrame);

                settingFrame.setVisibility(View.INVISIBLE);
                generalFrame.setVisibility(View.INVISIBLE);
                myPointsFrame.setVisibility(View.INVISIBLE);

                switch (position) {
                    case MY_POINTS:
                        myPointsFrame.setVisibility(View.VISIBLE);
                        break;
                    case SETTINGS:
                        settingFrame.setVisibility(View.VISIBLE);
                        break;
                    default:
                    case GENERAL:
                        generalFrame.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }
}
