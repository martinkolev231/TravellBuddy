package com.travellbuddy.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.travellbuddy.R;
import com.travellbuddy.models.User;
import com.travellbuddy.utils.Constants;
import com.travellbuddy.utils.FirebaseHelper;

/**
 * RegisterActivity handles user registration
 * Allows users to create new accounts with email and password
 */
public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private RadioGroup userTypeRadioGroup;
    private RadioButton regularUserRadioButton, adminRadioButton;
    private MaterialButton registerButton;
    private TextView loginTextView;
    private ProgressBar progressBar;
    
    private FirebaseHelper firebaseHelper;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase
        firebaseHelper = FirebaseHelper.getInstance();
        mAuth = firebaseHelper.getAuth();
        
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        userTypeRadioGroup = findViewById(R.id.userTypeRadioGroup);
        regularUserRadioButton = findViewById(R.id.regularUserRadioButton);
        adminRadioButton = findViewById(R.id.adminRadioButton);
        registerButton = findViewById(R.id.registerButton);
        loginTextView = findViewById(R.id.loginTextView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupClickListeners() {
        registerButton.setOnClickListener(v -> registerUser());
        
        loginTextView.setOnClickListener(v -> {
            finish(); // Go back to login
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError(getString(R.string.error_empty_field));
            nameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_empty_field));
            emailEditText.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            emailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.error_empty_field));
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < Constants.MIN_PASSWORD_LENGTH) {
            passwordEditText.setError(getString(R.string.error_short_password));
            passwordEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError(getString(R.string.error_empty_field));
            confirmPasswordEditText.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError(getString(R.string.error_password_mismatch));
            confirmPasswordEditText.requestFocus();
            return;
        }

        // Get selected user type
        String userType = regularUserRadioButton.isChecked() ? 
            Constants.USER_TYPE_USER : Constants.USER_TYPE_ADMIN;

        // Show progress
        showProgress(true);

        // Create Firebase user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration success
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Create user object
                            User user = new User(firebaseUser.getUid(), name, email, userType);
                            
                            // Save user to database
                            firebaseHelper.getUserRef(firebaseUser.getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(dbTask -> {
                                        showProgress(false);
                                        
                                        if (dbTask.isSuccessful()) {
                                            // Save user ID in SharedPreferences
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(Constants.PREF_USER_ID, firebaseUser.getUid());
                                            editor.putString(Constants.PREF_USER_TYPE, userType);
                                            editor.putString(Constants.PREF_USER_NAME, name);
                                            editor.apply();
                                            
                                            Toast.makeText(RegisterActivity.this, 
                                                getString(R.string.success_register), 
                                                Toast.LENGTH_SHORT).show();
                                            
                                            // Navigate to main activity
                                            navigateToMain();
                                        } else {
                                            Toast.makeText(RegisterActivity.this,
                                                getString(R.string.error_save_data) + ": " + 
                                                dbTask.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    } else {
                        // Registration failed
                        showProgress(false);
                        Toast.makeText(RegisterActivity.this,
                            getString(R.string.error_register_failed) + ": " + 
                            task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        registerButton.setEnabled(!show);
    }

    private void navigateToMain() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
