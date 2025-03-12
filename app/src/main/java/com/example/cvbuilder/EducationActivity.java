package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EducationActivity extends AppCompatActivity {
    private EditText etUniversity, etDatesAttended;
    private Button btnSubmitEducation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etUniversity = findViewById(R.id.etUniversity);
        etDatesAttended = findViewById(R.id.etDatesAttended);
        btnSubmitEducation = findViewById(R.id.btnSubmitEducation);

        // Set the OnClickListener for the Submit button
        btnSubmitEducation.setOnClickListener(v -> {
            // Retrieve the university name and dates attended from EditText
            String university = etUniversity.getText().toString();
            String datesAttended = etDatesAttended.getText().toString();

            // Check if the fields are not empty
            if (!university.isEmpty() && !datesAttended.isEmpty()) {
                // Create an Intent to return the result back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("university", university);
                resultIntent.putExtra("datesAttended", datesAttended);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the EducationActivity
            } else {
                // Show a toast if any field is empty
                Toast.makeText(EducationActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}