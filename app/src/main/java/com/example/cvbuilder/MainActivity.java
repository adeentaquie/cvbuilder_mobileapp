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

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> personalDetailsLauncher;
    private ActivityResultLauncher<Intent> summaryLauncher;
    private ActivityResultLauncher<Intent> educationLauncher;
    private ActivityResultLauncher<Intent> experienceLauncher;
    private ActivityResultLauncher<Intent> certificationsLauncher;

    Button  btnPersonalDetails,btnSummary , btnEducation, btnExperience,btnCertifications, btn_preview ;

    private String email, phoneNumber,summary,name,university,educationDatesAttendedFrom,educationDatesAttendedTo,organizationDatesAttendedFrom,organizationDatesAttendedTo,organization,certificationName;  // Variables to store the email and phone number
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




        personalDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(this, "Data not entered by user", Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent dataIntent = result.getData();
                        email = dataIntent.getStringExtra("email");
                        phoneNumber = dataIntent.getStringExtra("phoneNumber");
                        name = dataIntent.getStringExtra("name");  // Fixed here

                        Toast.makeText(this, name + "\n" + email + "\n" + phoneNumber + "\n", Toast.LENGTH_SHORT).show();
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
                        summary = dataIntent.getStringExtra("summary");

                        Toast.makeText(this, summary+"\n", Toast.LENGTH_SHORT).show();

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
                        university = dataIntent.getStringExtra("university");
                        educationDatesAttendedFrom= dataIntent.getStringExtra("educationDatesAttendedFrom");
                        educationDatesAttendedTo= dataIntent.getStringExtra("educationDatesAttendedTo");


                        Toast.makeText(this, educationDatesAttendedFrom+"\n"+educationDatesAttendedTo+"\n"+university+"\n", Toast.LENGTH_SHORT).show();

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
                        organization = dataIntent.getStringExtra("organization");
                        organizationDatesAttendedFrom = dataIntent.getStringExtra("datesAttendedFrom");
                        organizationDatesAttendedTo = dataIntent.getStringExtra("datesAttendedTo");

                        Toast.makeText(this, organizationDatesAttendedFrom+"\n" + organizationDatesAttendedTo, Toast.LENGTH_SHORT).show();

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
                        certificationName = dataIntent.getStringExtra("certificationName");

                        Toast.makeText(this, certificationName+"\n", Toast.LENGTH_SHORT).show();

                    }

                });



        // Set up the onClick listeners for each button to launch the activities


        btnPersonalDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
            personalDetailsLauncher.launch(intent);
        });
//
        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Summary.class);
            summaryLauncher.launch(intent);
        });
//
        btnEducation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EducationActivity.class);
            educationLauncher.launch(intent);
        });

        btnExperience.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
            experienceLauncher.launch(intent);
        });
//
        btnCertifications.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CertificationsActivity.class);
            certificationsLauncher.launch(intent);
        });
//
        btn_preview.setOnClickListener(v -> {
            // Check if any required field is null or empty
            if ( email == null || phoneNumber == null || summary == null ||
                    name == null || university == null || educationDatesAttendedFrom == null || educationDatesAttendedTo == null ||
                    organizationDatesAttendedFrom == null || organizationDatesAttendedTo == null || organization == null ||
                    certificationName == null  || email.isEmpty() ||
                    phoneNumber.isEmpty() || summary.isEmpty() || name.isEmpty() || university.isEmpty() ||
                    educationDatesAttendedFrom.isEmpty() || educationDatesAttendedTo.isEmpty() ||
                    organizationDatesAttendedFrom.isEmpty() || organizationDatesAttendedTo.isEmpty() || organization.isEmpty() ||
                    certificationName.isEmpty()) {

                // Show a Toast message indicating that some data is missing
                Toast.makeText(MainActivity.this, "Please fill in all the fields before proceeding!", Toast.LENGTH_SHORT).show();

                // Optionally return or do some other action if data is incomplete
                return;
            }

            // Create the intent to send the data to PreviewActivity
            Intent intent = new Intent(MainActivity.this, PreviewActivity.class);

            // Put all the data into the intent as extras

            intent.putExtra("email", email);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("summary", summary);
            intent.putExtra("name", name);
            intent.putExtra("university", university);
            intent.putExtra("educationDatesAttendedFrom", educationDatesAttendedFrom);
            intent.putExtra("educationDatesAttendedTo", educationDatesAttendedTo);
            intent.putExtra("organizationDatesAttendedFrom", organizationDatesAttendedFrom);
            intent.putExtra("organizationDatesAttendedTo", organizationDatesAttendedTo);
            intent.putExtra("organization", organization);
            intent.putExtra("certificationName", certificationName);

            // Start PreviewActivity with the intent
            startActivity(intent);
            finish(); // Close the current activity (optional)
        });


    }
    private void init(){
         btnPersonalDetails = findViewById(R.id.btn_personal_details);
         btnSummary = findViewById(R.id.btn_summary);
         btnEducation = findViewById(R.id.btn_education);
         btnExperience = findViewById(R.id.btn_experience);
         btnCertifications = findViewById(R.id.btn_certifications);
         btn_preview=findViewById(R.id.btn_preview);
    }
}