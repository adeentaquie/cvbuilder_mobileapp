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

public class CertificationsActivity extends AppCompatActivity {
    private EditText etCertificationName;
    private Button btnSubmitCertification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etCertificationName = findViewById(R.id.etCertificationName);
        btnSubmitCertification = findViewById(R.id.btnSubmitCertification);

        // Set the OnClickListener for the Submit button
        btnSubmitCertification.setOnClickListener(v -> {
            // Retrieve the certification name and date achieved from EditText
            String certificationName = etCertificationName.getText().toString();

            // Check if the fields are not empty
            if (!certificationName.isEmpty() ) {
                // Create an Intent to return the result back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("certificationName", certificationName);

                // Set the result and finish the activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the CertificationsActivity
            } else {
                // Show a toast if any field is empty
                Toast.makeText(CertificationsActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}