package com.example.parentcontrol;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenTimeActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private int timerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        TextView timerDisplay = findViewById(R.id.timerDisplay);

        startButton.setOnClickListener(v -> startMonitoring(timerDisplay));
        stopButton.setOnClickListener(v -> stopMonitoring());
    }

    private void startMonitoring(TextView timerDisplay) {
        handler = new Handler();
        runnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                timerCount++;
                timerDisplay.setText("Time: " + timerCount + " minutes");
                handler.postDelayed(this, 60000); // Update every minute
            }
        };
        handler.post(runnable);
    }

    private void stopMonitoring() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
            timerCount = 0; // Reset timer
        }
    }
}
