package com.example.parentcontrol;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Build.VERSION_CODES.R);

        Button screenTimeButton = findViewById(R.id.screenTimeButton);
        Button gamesButton = findViewById(R.id.gamesButton);
        Button reportsButton = findViewById(R.id.reportsButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        screenTimeButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ScreenTimeActivity.class)));

        gamesButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, GamesActivity.class)));

        reportsButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AIInsightsActivity.class)));

        settingsButton.setOnClickListener(v -> {
            // Add settings activity if needed
        });
    }
}
