package com.example.parentcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parentalcontrolapp.R;

public class GamesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Button memoryGameButton = findViewById(R.id.memoryGameButton);
        memoryGameButton.setOnClickListener(v -> startActivity(new Intent(GamesActivity.this, MemoryGameActivity.class)));

        // Add more game buttons as needed
    }
}
