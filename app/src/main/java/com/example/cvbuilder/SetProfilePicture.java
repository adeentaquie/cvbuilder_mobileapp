package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetProfilePicture extends AppCompatActivity {

    private ImageView profileImageView;
    private FloatingActionButton fabAddProfilePic;

    // Declare the ActivityResultLauncher
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Check if the user has selected an image and the result is OK
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Get the URI of the selected image
                    Uri selectedImageUri = result.getData().getData();

                    // Set the image in the ImageView
                    profileImageView.setImageURI(selectedImageUri);
                    new Handler()
                            .postDelayed(()->{
                                Intent resultIntent = new Intent();
                                resultIntent.setData(selectedImageUri);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            },2000);


                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_picture);

        // Initialize views
        profileImageView = findViewById(R.id.profile_image);
        fabAddProfilePic = findViewById(R.id.fab_addprofilepic);

        // Set an onClick listener on the FloatingActionButton to trigger the gallery intent
        fabAddProfilePic.setOnClickListener(v -> {
            // Create an implicit intent to pick an image from the gallery
            Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Launch the intent using the ActivityResultLauncher
            pickImageLauncher.launch(pickImageIntent);
        });
    }
}