package com.example.androidprojet.ui.App;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprojet.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.SignInButton;
        final Button Register = binding.Register;

        usernameEditText.setOnEditorActionListener(
                (v, actionId, event) -> false
        );

        passwordEditText.setOnEditorActionListener(
                (v, actionId, event) -> {
                    Toast.makeText(LoginActivity.this, "Authentication",
                            Toast.LENGTH_SHORT).show();
                    if (passwordEditText.getText().length() > 0)
                        SignIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                    return false;
                }
        );


        loginButton.setOnClickListener(v ->
                SignIn(usernameEditText.getText().toString(), passwordEditText.getText().toString())

        );

        Register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
            startActivity(intent);
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUiWithUser(currentUser);
        }
    }

    private void updateUiWithUser(FirebaseUser user) {

        if (user != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }

    public void SignIn(String email, String password) {

        final TextView Errormessage = binding.ErrorMessage;
        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        private static final String TAG = "Login";


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUiWithUser(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                Errormessage.setVisibility(View.VISIBLE);
                                updateUiWithUser(null);
                            }
                        }
                    });
        } else {
            Errormessage.setVisibility(View.VISIBLE);
        }

    }
}