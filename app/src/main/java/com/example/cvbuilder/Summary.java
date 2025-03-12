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

public class Summary extends AppCompatActivity {

    private EditText etSummary;
    private Button btnSubmitSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etSummary = findViewById(R.id.etSummary);
        btnSubmitSummary = findViewById(R.id.btnSubmitSummary);

        // Set the OnClickListener for the Submit button
        btnSubmitSummary.setOnClickListener(v -> {
            // Retrieve the summary text from EditText
            String summaryText = etSummary.getText().toString();

            // Check if the summary field is not empty
            if (!summaryText.isEmpty()) {
                // Create an Intent to return the result back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("summary", summaryText);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the SummaryActivity
            } else {
                // Show a toast if the field is empty
                Toast.makeText(this, "Please enter a summary", Toast.LENGTH_SHORT).show();
            }
        });
    }
}