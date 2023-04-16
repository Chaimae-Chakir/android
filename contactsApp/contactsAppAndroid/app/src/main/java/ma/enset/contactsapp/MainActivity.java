package ma.enset.contactsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactClickListener {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contacts = new ArrayList<>();
    private FloatingActionButton addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contact_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter = new ContactAdapter(contacts, this);
        contactAdapter.setContactClickListener(this);
        recyclerView.setAdapter(contactAdapter);

        addContactButton = findViewById(R.id.fab_add_contact);
        addContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContact.class);
            startActivity(intent);
        });

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

    @Override
    public void onContactClick(Contact contact) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    @Override
    public void onEditClick(Contact contact) {
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }
    @Override
    public void onDeleteClick(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm deletion");
        builder.setMessage("Are you sure you want to delete this contact?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteContact(contact);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteContact(Contact contact) {
        ContactService contactService = MyApp.getContactService();
        if (contactService == null) {
            Toast.makeText(MainActivity.this, "Contact service is not available", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Void> call = contactService.deleteContact(contact.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
                    contactAdapter.removeContact(contact);
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetailsClick(Contact contact) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

}
