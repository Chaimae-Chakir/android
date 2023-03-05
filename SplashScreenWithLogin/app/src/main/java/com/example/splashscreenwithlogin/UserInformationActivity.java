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
        if (intent != null) {
            String email = intent.getStringExtra("email");
            int index = email.indexOf("@");
            String nom = email.substring(0, index);
            String password = intent.getStringExtra("password");

            String n = "Nom de l'utilisateur est " + nom ;
            String p =  "Mot de passe est " + password;

            emailTextView.setText(n);
            passwordTextView.setText(p);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformationActivity.this, Activity_Splash_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
