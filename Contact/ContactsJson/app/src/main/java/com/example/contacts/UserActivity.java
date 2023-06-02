package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class UserActivity extends Activity {
    private Button call;
    private TextView name,profession, email,phone_profile;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setAllViews();

        Bundle bundle = getIntent().getExtras();
        name.setText(bundle.getString("name"));
        profession.setText(bundle.getString("profession"));
        phone_profile.setText(bundle.getString("phone_profile"));
        email.setText(bundle.getString("email"));
        String imageName = bundle.getString("profile_image");
        int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(imageResource);

    }

    private void setAllViews() {
        name = findViewById(R.id.name);
        profession = findViewById(R.id.profession);
        email = findViewById(R.id.email);
        phone_profile = findViewById(R.id.phone_profile);
        imageView = findViewById(R.id.profile_image);
    }

    public void Call(View view) {
        setAllViews();
        call = findViewById(R.id.call);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone_profile.getText()));
        startActivity(intent);
    }
}

