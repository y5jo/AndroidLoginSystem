package com.landoy.Login_system;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_login_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_details_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText lastNameInput = findViewById(R.id.last_name_input);
        TextInputEditText firstNameInput = findViewById(R.id.first_name_input);
        TextInputEditText middleNameInput = findViewById(R.id.middle_name_input);
        MaterialAutoCompleteTextView programInput = findViewById(R.id.program_input);
        TextInputEditText yearLevelInput = findViewById(R.id.year_level_input);
        MaterialButton continueButton = findViewById(R.id.continue_btn);
        View summaryContainer = findViewById(R.id.details_summary);
        TextView lastNameResult = findViewById(R.id.last_name_result);
        TextView firstNameResult = findViewById(R.id.first_name_result);
        TextView middleNameResult = findViewById(R.id.middle_name_result);
        TextView programResult = findViewById(R.id.program_result);
        TextView yearLevelResult = findViewById(R.id.year_level_result);

        continueButton.setText("Finish");
        summaryContainer.setVisibility(View.GONE);

        String[] programs = {
                "BS Information Technology - Mobile Web Application",
                "BS Computer Science - Digital Forensic",
                "BS Computer Science - Machine Learning",
        };
        programInput.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                programs
        ));

        continueButton.setOnClickListener(v -> {
            TextInputEditText[] requiredInputs = {
                    lastNameInput,
                    firstNameInput,
                    middleNameInput,
                    yearLevelInput
            };

            for (TextInputEditText input : requiredInputs) {
                if (input.getText() == null || input.getText().toString().trim().isEmpty()) {
                    input.setError("This field is required");
                    input.requestFocus();
                    return;
                }
            }

            if (programInput.getText() == null || programInput.getText().toString().trim().isEmpty()) {
                programInput.setError("Select a program");
                programInput.requestFocus();
                return;
            }

            lastNameResult.setText("Last Name: " + lastNameInput.getText().toString().trim());
            firstNameResult.setText("First Name: " + firstNameInput.getText().toString().trim());
            middleNameResult.setText("Middle Name: " + middleNameInput.getText().toString().trim());
            programResult.setText("Program: " + programInput.getText().toString().trim());
            yearLevelResult.setText("Year Level: " + yearLevelInput.getText().toString().trim());
            summaryContainer.setVisibility(View.VISIBLE);
            continueButton.setText("Finish");
        });
    }
}
