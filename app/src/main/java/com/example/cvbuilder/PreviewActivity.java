package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PreviewActivity extends AppCompatActivity {

    // Declare TextViews and ImageView
    TextView tvname, tvemail, tvphonenumber, tvsummary, tvuniversity, tveducationdatesattendedfrom,
            tveducationdatesattendedto, tvorganizationdatesattendedfrom, tvorganizationdatesattendedto,
            tvorganization, tvcertification;
    private Uri profilePictureUri;
    ImageView profilepicture;

    // Declare strings to hold data passed through Intent
    private String email, phoneNumber, summary, name, university, educationDatesAttendedFrom, educationDatesAttendedTo,
            organizationDatesAttendedFrom, organizationDatesAttendedTo, organization, certificationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);

        // Adjust padding for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve data passed through the Intent
        Intent intent = getIntent();
//        profilePictureUri = Uri.parse(intent.getStringExtra("profilePictureUri"));
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

        // Set the profile picture (if URI is provided)
//        if (profilePictureUri != null) {
//            profilepicture.setImageURI(profilePictureUri);
//        }
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
    }
}