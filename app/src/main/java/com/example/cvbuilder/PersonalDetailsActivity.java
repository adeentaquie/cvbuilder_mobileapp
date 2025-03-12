package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalDetailsActivity extends AppCompatActivity {
    private EditText etEmail, etPhoneNumber;
    private TextView tv_result;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSubmit = findViewById(R.id.btnSubmit);
        tv_result=findViewById(R.id.tv_result);

        // Set the OnClickListener for the Submit button
        btnSubmit.setOnClickListener(v -> {
            // Retrieve the email and phone number from EditText
            String email = etEmail.getText().toString();
            String phoneNumber = etPhoneNumber.getText().toString();

            // Check if the fields are not empty
            if (!email.isEmpty() && !phoneNumber.isEmpty()) {
                // Show email in the TextView for confirmation before sending
                tv_result.setText(email); // Display the email in TextView

                // Delay the result sending for 1 second (1000 milliseconds)
                new Handler().postDelayed(() -> {
                    // Create an Intent to return the result back to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("email", email);
                    resultIntent.putExtra("phoneNumber", phoneNumber);

                    // Set the result and finish the activity
                    setResult(RESULT_OK, resultIntent);
                    finish();  // Close the activity after returning the result
                }, 1000);  // 1-second delay

            } else {
                // Show a toast if fields are empty
                Toast.makeText(PersonalDetailsActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            };

        });
    }
}