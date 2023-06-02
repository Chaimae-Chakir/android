package com.example.splashscreenwithlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInformationActivity extends AppCompatActivity {

    private TextView emailTextView;
    private TextView passwordTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        emailTextView = findViewById(R.id.email_text_view);
        passwordTextView = findViewById(R.id.password_text_view);
        backButton = findViewById(R.id.back_button);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("email") && intent.hasExtra("password")) {
            String email = intent.getStringExtra("email");
            int index = email.indexOf("@");
            if (index != -1) {
                String nom = email.substring(0, index);
                String password = intent.getStringExtra("password");

                String n = "Nom de l'utilisateur est " + nom;
                String p = "Mot de passe est " + password;

                emailTextView.setText(n);
                passwordTextView.setText(p);
            }
        }


        backButton.setOnClickListener(v -> {
                Intent intent1 = new Intent(UserInformationActivity.this, Activity_Splash_Login.class);
                startActivity(intent1);
                finish();
            });
        }
    }


