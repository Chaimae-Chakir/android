package ma.enset.contactsapp;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contact_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);

        getAllContacts();
    }

    private void getAllContacts() {
        ContactService contactService = MyApp.getContactService();
        if (contactService == null) {
            Toast.makeText(MainActivity.this, "Contact service is not available", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<List<Contact>> call = contactService.getAllContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                contactAdapter.setContacts(contacts);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Other methods for creating, updating, and deleting contacts
}
