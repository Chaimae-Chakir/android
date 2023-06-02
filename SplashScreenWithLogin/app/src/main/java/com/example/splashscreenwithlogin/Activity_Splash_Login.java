package com.example.splashscreenwithlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Splash_Login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Vérifier si l'email et le mot de passe sont valides, par exemple en utilisant un Regex
                // Si les données sont valides, ouvrir une nouvelle activité et passer les données d'utilisateur en tant qu'arguments
                if (isValidEmail(email) && isValidPassword(password)) {
                    Intent intent = new Intent(Activity_Splash_Login.this, com.example.splashscreenwithlogin.UserInformationActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else {
                    // Afficher un message d'erreur si les données ne sont pas valides
                    Toast.makeText(Activity_Splash_Login.this, "Email ou mot de passe invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        // Vérifier si l'email est valide en utilisant un Regex ou une autre méthode
        return true;
    }

    private boolean isValidPassword(String password) {
        // Vérifier si le mot de passe est valide en utilisant une méthode appropriée
        return true;
    }
}
