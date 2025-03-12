package com.example.cvbuilder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExperienceActivity extends AppCompatActivity {

    private EditText etOrganization, etDatesAttendedFrom, etDatesAttendedTo;
    private Button btnSubmitExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the views
        etOrganization = findViewById(R.id.etOrganization);
        etDatesAttendedFrom = findViewById(R.id.etDatesAttendedFrom);
        etDatesAttendedTo = findViewById(R.id.etDatesAttendedTo);
        btnSubmitExperience = findViewById(R.id.btnSubmitExperience);

        // Set OnClickListeners for both Date fields to open the DatePickerDialog
        etDatesAttendedFrom.setOnClickListener(v -> showDatePickerDialog(etDatesAttendedFrom));
        etDatesAttendedTo.setOnClickListener(v -> showDatePickerDialog(etDatesAttendedTo));

        // Set the OnClickListener for the Submit button
        btnSubmitExperience.setOnClickListener(v -> {
            // Retrieve the organization name and dates attended from EditText
            String organization = etOrganization.getText().toString();
            String datesAttendedFrom = etDatesAttendedFrom.getText().toString();
            String datesAttendedTo = etDatesAttendedTo.getText().toString();

            // Check if the fields are not empty
            if (!organization.isEmpty() && !datesAttendedFrom.isEmpty() && !datesAttendedTo.isEmpty()) {
                // Create an Intent to return the result back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("organization", organization);
                resultIntent.putExtra("datesAttendedFrom", datesAttendedFrom);
                resultIntent.putExtra("datesAttendedTo", datesAttendedTo);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the ExperienceActivity
            } else {
                // Show a toast if any field is empty
                Toast.makeText(ExperienceActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to show the DatePickerDialog
    private void showDatePickerDialog(EditText targetEditText) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(ExperienceActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Format the selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, monthOfYear, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()); // You can change the date format here
                targetEditText.setText(sdf.format(selectedDate.getTime()));  // Set the formatted date in the target EditText
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}