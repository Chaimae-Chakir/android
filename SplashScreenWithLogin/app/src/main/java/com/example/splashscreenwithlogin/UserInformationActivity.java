package com.example.splashscreenwithlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.splashscreenwithlogin.Activity_Splash_Login;
import com.example.splashscreenwithlogin.R;

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
        if (intent != null) {
            String email = intent.getStringExtra("email");
            String nom = "";
            if (email != null && email.contains("@")) {
                int index = email.indexOf("@");
                nom = email.substring(0, index);
            }
            String password = intent.getStringExtra("password");

            String n = "Nom de l'utilisateur est " + nom;
            String p = "Mot de passe est " + password;

            emailTextView.setText(n);
            passwordTextView.setText(p);
        }

        backButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(UserInformationActivity.this, Activity_Splash_Login.class);
            startActivity(intent1);
            finish();
        });
    }
}
