package com.example.cvbuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> profilePictureLauncher;
    private ActivityResultLauncher<Intent> personalDetailsLauncher;
    private ActivityResultLauncher<Intent> summaryLauncher;
    private ActivityResultLauncher<Intent> educationLauncher;
    private ActivityResultLauncher<Intent> experienceLauncher;
    private ActivityResultLauncher<Intent> certificationsLauncher;
    private ActivityResultLauncher<Intent> referencesLauncher;

    Button btnProfilePicture, btnPersonalDetails,btnSummary , btnEducation, btnExperience,btnCertifications, btnReferences ;
    private Uri selectedProfilePictureUri; // Variable to store the result

    private String email, phoneNumber;  // Variables to store the email and phone number

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        init();

        profilePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Get the image URI returned from SetProfilePictureActivity
                        selectedProfilePictureUri = result.getData().getData();
                        Toast.makeText(this, "Image is Uploaded", Toast.LENGTH_SHORT).show();

                        // Update the profile image in the MainActivity
                    }

                });

        personalDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }
                });

        summaryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }
                });

        educationLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }
                });

        experienceLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }
                });

        certificationsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }

                });

        referencesLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");

                        Toast.makeText(this, email+"\n"+phoneNumber+"\n", Toast.LENGTH_SHORT).show();

                    }
                });

        // Set up the onClick listeners for each button to launch the activities
        btnProfilePicture.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetProfilePicture.class);
            profilePictureLauncher.launch(intent);
        });

        btnPersonalDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
            personalDetailsLauncher.launch(intent);
        });
//
//        btnSummary.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
//            summaryLauncher.launch(intent);
//        });
//
//        btnEducation.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, EducationActivity.class);
//            educationLauncher.launch(intent);
//        });
//
//        btnExperience.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
//            experienceLauncher.launch(intent);
//        });
//
//        btnCertifications.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CertificationsActivity.class);
//            certificationsLauncher.launch(intent);
//        });
//
//        btnReferences.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ReferencesActivity.class);
//            referencesLauncher.launch(intent);
//        });
    }
    private void init(){
         btnProfilePicture = findViewById(R.id.btn_profile_picture);
         btnPersonalDetails = findViewById(R.id.btn_personal_details);
         btnSummary = findViewById(R.id.btn_summary);
         btnEducation = findViewById(R.id.btn_education);
         btnExperience = findViewById(R.id.btn_experience);
         btnCertifications = findViewById(R.id.btn_certifications);
         btnReferences = findViewById(R.id.btn_references);
    }
}