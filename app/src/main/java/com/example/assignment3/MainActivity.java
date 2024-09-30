package com.example.assignment3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etId;
    private Button btnSubmit;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etId = findViewById(R.id.etId);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvMessage = findViewById(R.id.tvMessage);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    tvMessage.setText("Submission done!");
                }
            }
        });
    }

    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String id = etId.getText().toString().trim();


        String nameRegex = "^[a-zA-Z\\s]+$";

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";

        String idRegex = "^[a-zA-Z0-9]{1,17}$";

        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);

        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);

        Pattern idPattern = Pattern.compile(idRegex);
        Matcher idMatcher = idPattern.matcher(id);

        if (TextUtils.isEmpty(name) || !nameMatcher.matches()) {
            Toast.makeText(this, "Invalid name (only letters and spaces allowed)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(email) || !emailMatcher.matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(id) || !idMatcher.matches()) {
            Toast.makeText(this, "Invalid ID (alphanumeric, 1-10 characters)", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}