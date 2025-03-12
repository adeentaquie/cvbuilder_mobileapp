package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    ImageView iv_logo;
    Animation logoanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize views and animation
        init();

        // Set up window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ensure ImageView is initialized before setting the animation
        if (iv_logo != null && logoanimation != null) {
            iv_logo.startAnimation(logoanimation); // Start the animation
        } else {
            // Log an error or handle initialization failure
            System.out.println("ImageView or Animation is null");
        }

        // Transition to the next screen after a delay
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }, 5000);
    }

    private void init() {
        iv_logo = findViewById(R.id.iv_logo);  // Initialize the ImageView
        logoanimation = AnimationUtils.loadAnimation(this, R.anim.logoanimation);  // Load the animation

        // Add checks or logs to confirm if initialization is successful
        if (iv_logo == null) {
            System.out.println("iv_logo is null");
        }
        if (logoanimation == null) {
            System.out.println("logoanimation is null");
        }
    }
}
