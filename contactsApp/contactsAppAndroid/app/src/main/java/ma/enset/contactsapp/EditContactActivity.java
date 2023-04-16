package ma.enset.contactsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditContactActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText,jobEditText;
    private Button updateButton;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcontactactivity);

        nameEditText = findViewById(R.id.edit_text_name);
        phoneEditText = findViewById(R.id.edit_text_phone);
        emailEditText = findViewById(R.id.edit_text_email);
        jobEditText = findViewById(R.id.edit_text_job);
        updateButton = findViewById(R.id.button_save);

        contact = (Contact) getIntent().getSerializableExtra("contact");

        nameEditText.setText(contact.getName());
        phoneEditText.setText(contact.getPhone());
        emailEditText.setText(contact.getEmail());
        updateButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String job = jobEditText.getText().toString();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(EditContactActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            ContactService contactService = MyApp.getContactService();
            if (contactService == null) {
                Toast.makeText(EditContactActivity.this, "Contact service is not available", Toast.LENGTH_SHORT).show();
                return;
            }
            Contact updatedContact = new Contact(name, phone, email,job);
            int id = contact.getId();
            Call<Void> call = contactService.updateContact(id, updatedContact); // Pass the integer id to the method

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(EditContactActivity.this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditContactActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(EditContactActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
