package ma.enset.contactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContact extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText,jobEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        nameEditText = findViewById(R.id.name_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        jobEditText = findViewById(R.id.job_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String job = jobEditText.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email)) {
                Toast.makeText(AddContact.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Contact contact = new Contact(name, phone, email,job);
                createContact(contact);
            }
        });
    }

    private void createContact(Contact contact) {
        ContactService contactService = MyApp.getContactService();
        if (contactService == null) {
            Toast.makeText(AddContact.this, "Contact service is not available", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Contact> call = contactService.createContact(contact);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact newContact = response.body();
                Toast.makeText(AddContact.this, "Contact created: " + newContact.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddContact.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(AddContact.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

