package com.example.parentcontrol;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MemoryGameActivity extends AppCompatActivity {

    private final Button[] buttons = new Button[12];
    private int[] buttonValues = new int[12];
    private Button firstSelectedButton;
    private int firstSelectedValue;
    private boolean isBusy = false;
    private int numberOfMatchedPairs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        // Initialize grid layout for the buttons
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) GridLayout gridLayout = findViewById(R.id.memoryGrid);
        setupGame(gridLayout);
    }

    private void setupGame(GridLayout gridLayout) {
        // Populate buttonValues with pairs of numbers
        Integer[] values = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6};
        List<Integer> valueList = Arrays.asList(values);
        Collections.shuffle(valueList); // Shuffle values to randomize the board
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            buttonValues = valueList.stream().mapToInt(i -> i).toArray(); // Convert to int array
        }

        // Create buttons and set click listeners
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i] = new Button(this);
            buttons[i].setText("?");
            buttons[i].setTextSize(24);
            buttons[i].setOnClickListener(v -> {
                if (!isBusy) {
                    handleButtonClick(index);
                }
            });
            gridLayout.addView(buttons[i]);
        }
    }

    private void handleButtonClick(final int index) {
        // If the button is already matched, don't do anything
        if (buttons[index].getText().toString().equals(String.valueOf(buttonValues[index]))) {
            return;
        }

        // Show the value on the button
        buttons[index].setText(String.valueOf(buttonValues[index]));

        if (firstSelectedButton == null) {
            // This is the first button selected
            firstSelectedButton = buttons[index];
            firstSelectedValue = buttonValues[index];
        } else {
            // This is the second button selected
            isBusy = true;

            if (firstSelectedValue == buttonValues[index]) {
                // It's a match!
                firstSelectedButton = null;
                isBusy = false;
                numberOfMatchedPairs++;

                if (numberOfMatchedPairs == 6) {
                    // All pairs have been matched!
                    Toast.makeText(MemoryGameActivity.this, "You won!", Toast.LENGTH_LONG).show();
                }
            } else {
                // Not a match, reset buttons after a delay
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    firstSelectedButton.setText("?");
                    buttons[index].setText("?");
                    firstSelectedButton = null;
                    isBusy = false;
                }, 1000); // 1 second delay
            }
        }
    }
}
