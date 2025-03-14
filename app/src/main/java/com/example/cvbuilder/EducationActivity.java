package com.example.cvbuilder;

import android.annotation.SuppressLint;
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

public class EducationActivity extends AppCompatActivity {
    private EditText etUniversity, etEducationDatesAttendedFrom, etEducationDatesAttendedTo;
    private Button btnSubmitEducation;

    @SuppressLint("MissingInflatedId")
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

        // Initialize the EditTexts and Button
        etUniversity = findViewById(R.id.etUniversity);
        etEducationDatesAttendedFrom = findViewById(R.id.etEducationDatesAttendedFrom);
        etEducationDatesAttendedTo = findViewById(R.id.etEducationDatesAttendedTo);
        btnSubmitEducation = findViewById(R.id.btnSubmitEducation);

        // Set OnClickListeners for both Date fields to open the DatePickerDialog
        etEducationDatesAttendedFrom.setOnClickListener(v -> showDatePickerDialog(etEducationDatesAttendedFrom));
        etEducationDatesAttendedTo.setOnClickListener(v -> showDatePickerDialog(etEducationDatesAttendedTo));

        // Set the OnClickListener for the Submit button
        btnSubmitEducation.setOnClickListener(v -> {
            // Retrieve the university name and dates attended from EditText
            String university = etUniversity.getText().toString();
            String datesAttendedFrom = etEducationDatesAttendedFrom.getText().toString();
            String datesAttendedTo = etEducationDatesAttendedTo.getText().toString();

            // Check if the fields are not empty
            if (!university.isEmpty() && !datesAttendedFrom.isEmpty() && !datesAttendedTo.isEmpty()) {
                // Create an Intent to return the result back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("university", university);
                resultIntent.putExtra("educationDatesAttendedFrom", datesAttendedFrom);
                resultIntent.putExtra("educationDatesAttendedTo", datesAttendedTo);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the EducationActivity
            } else {
                // Show a toast if any field is empty
                Toast.makeText(EducationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(EducationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
