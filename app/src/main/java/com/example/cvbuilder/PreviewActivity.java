package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class PreviewActivity extends AppCompatActivity {

    // Declare TextViews and ImageView
    TextView tvname, tvemail, tvphonenumber, tvsummary, tvuniversity, tveducationdatesattendedfrom,
            tveducationdatesattendedto, tvorganizationdatesattendedfrom, tvorganizationdatesattendedto,
            tvorganization, tvcertification;
    Button btn_sharecv;

    private ActivityResultLauncher<Intent> profilePictureLauncher;
    private FloatingActionButton fabAddProfilePic;  // FloatingActionButton to add profile picture
    private Uri profilePictureUri;  // URI to store the profile picture
    private ImageView profilepicture;  // ImageView to display the profile picture

    // Declare strings to hold data passed through Intent
    private String email, phoneNumber, summary, name, university, educationDatesAttendedFrom, educationDatesAttendedTo,
            organizationDatesAttendedFrom, organizationDatesAttendedTo, organization, certificationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        // Adjust padding for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve data passed through the Intent
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        phoneNumber = intent.getStringExtra("phoneNumber");
        summary = intent.getStringExtra("summary");
        name = intent.getStringExtra("name");
        university = intent.getStringExtra("university");
        educationDatesAttendedFrom = intent.getStringExtra("educationDatesAttendedFrom");
        educationDatesAttendedTo = intent.getStringExtra("educationDatesAttendedTo");
        organizationDatesAttendedFrom = intent.getStringExtra("organizationDatesAttendedFrom");
        organizationDatesAttendedTo = intent.getStringExtra("organizationDatesAttendedTo");
        organization = intent.getStringExtra("organization");
        certificationName = intent.getStringExtra("certificationName");

        // Initialize the views
        init();

        // Set the retrieved data into the views
        tvname.setText(name);
        tvemail.setText(email);
        tvphonenumber.setText(phoneNumber);
        tvsummary.setText(summary);
        tvuniversity.setText(university);
        tveducationdatesattendedfrom.setText(educationDatesAttendedFrom);
        tveducationdatesattendedto.setText(educationDatesAttendedTo);
        tvorganizationdatesattendedfrom.setText(organizationDatesAttendedFrom);
        tvorganizationdatesattendedto.setText(organizationDatesAttendedTo);
        tvorganization.setText(organization);
        tvcertification.setText(certificationName);

        // Initialize profile picture URI
        String profilePictureUriString = intent.getStringExtra("profilePictureUri");
        if (profilePictureUriString != null && !profilePictureUriString.isEmpty()) {
            profilePictureUri = Uri.parse(profilePictureUriString);
            profilepicture.setImageURI(profilePictureUri);  // Set the profile image if URI exists
        }

        // Setup FloatingActionButton to add or change profile picture
        fabAddProfilePic.setOnClickListener(v -> {
            // Launch the gallery picker to select an image
            Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            profilePictureLauncher.launch(pickImageIntent);
        });

        // Register the ActivityResultLauncher to handle the result of image picking
        profilePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Get the image URI returned from the gallery picker
                        profilePictureUri = result.getData().getData();
                        profilepicture.setImageURI(profilePictureUri);  // Set the selected image in the ImageView
                        Toast.makeText(PreviewActivity.this, "Profile picture updated", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case where no image was selected (optional)
                        Toast.makeText(PreviewActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Set up the share CV button click listener
        btn_sharecv.setOnClickListener(v -> {
            // Capture the screen as a Bitmap
            Bitmap bitmap = captureScreen();

            // Save the captured bitmap as an image file
            File imageFile = saveImageToFile(bitmap);

            if (imageFile != null) {
                // Share the image file
                shareFile(imageFile);
            } else {
                Toast.makeText(PreviewActivity.this, "Failed to capture screen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bitmap captureScreen() {
        // Create a bitmap from the view hierarchy
        Bitmap bitmap = Bitmap.createBitmap(findViewById(R.id.main).getWidth(),
                findViewById(R.id.main).getHeight(), Bitmap.Config.ARGB_8888);

        // Draw the view content into the bitmap
        Canvas canvas = new Canvas(bitmap);
        Drawable drawable = findViewById(R.id.main).getBackground();
        if (drawable != null) {
            drawable.draw(canvas);
        }
        findViewById(R.id.main).draw(canvas); // Draw the actual content

        return bitmap;
    }

    private File saveImageToFile(Bitmap bitmap) {
        File externalStorageDir = new File(getExternalFilesDir(null), "cv_image.png");

        try (FileOutputStream fos = new FileOutputStream(externalStorageDir)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);  // Save as PNG
            fos.flush();
            return externalStorageDir;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void shareFile(File file) {
        // Get the content URI for the file using FileProvider
        Uri fileUri = FileProvider.getUriForFile(this, "com.example.cvbuilder.provider", file);

        // Create an intent to share the image
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);  // Attach the file
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my CV!");  // Optional message
        shareIntent.setType("image/png");  // MIME type for image (PNG)

        // Use Intent.createChooser to allow the user to choose the app to share with (WhatsApp or Email)
        Intent chooser = Intent.createChooser(shareIntent, "Share CV via");

        // Check if the intent can be handled
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);  // Let the user choose an app to share with
        } else {
            Toast.makeText(PreviewActivity.this, "No app available to share CV", Toast.LENGTH_SHORT).show();
        }
    }


    // Initialize all the views
    private void init() {
        tvname = findViewById(R.id.tvname);
        tvemail = findViewById(R.id.tvemail);
        tvphonenumber = findViewById(R.id.tvphonenumber);
        tvsummary = findViewById(R.id.tvsummary);
        tvuniversity = findViewById(R.id.tveducation);
        tveducationdatesattendedfrom = findViewById(R.id.tveducationdatefrom);
        tveducationdatesattendedto = findViewById(R.id.tveducationdateto);
        tvorganizationdatesattendedfrom = findViewById(R.id.tvorganizationdatefrom);
        tvorganizationdatesattendedto = findViewById(R.id.tvorganizationdateto);
        tvorganization = findViewById(R.id.tvorganizationname);
        tvcertification = findViewById(R.id.tvcertifications);
        profilepicture = findViewById(R.id.profilepicture);
        fabAddProfilePic = findViewById(R.id.fabaddprofilepic);  // Initialize the FloatingActionButton
        btn_sharecv = findViewById(R.id.btn_sharecv);  // Initialize the Share CV button
    }
}
