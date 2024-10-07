package com.example.parentcontrol;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.mediationtestsuite.activities.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText emailField = findViewById(R.id.emailField);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText passwordField = findViewById(R.id.passwordField);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button loginButton = findViewById(R.id.loginButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView signupLink = findViewById(R.id.signupLink);

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            signIn(email, password);
        });

        signupLink.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });
    }

    private void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish(); // Close the login activity
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
