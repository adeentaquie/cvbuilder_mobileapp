package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PreviewActivity extends AppCompatActivity {

    // Declare TextViews and ImageView
    TextView tvname, tvemail, tvphonenumber, tvsummary, tvuniversity, tveducationdatesattendedfrom,
            tveducationdatesattendedto, tvorganizationdatesattendedfrom, tvorganizationdatesattendedto,
            tvorganization, tvcertification;

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
    }
}
