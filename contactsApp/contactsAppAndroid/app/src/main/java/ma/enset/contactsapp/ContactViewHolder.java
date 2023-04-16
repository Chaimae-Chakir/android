package ma.enset.contactsapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import ma.enset.contactsapp.ContactAdapter.ContactClickListener;

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView nameTextView;
    private Button editButton;
    private Button deleteButton;
    private Button detailsButton;
    private ContactClickListener contactClickListener;
    private List<Contact> contacts;

    public ContactViewHolder(@NonNull View itemView, List<Contact> contacts, ContactAdapter.ContactClickListener contactClickListener) {
        super(itemView);
        this.contacts = contacts;
        this.contactClickListener = contactClickListener;
        nameTextView = itemView.findViewById(R.id.contact_name);
        editButton = itemView.findViewById(R.id.button_edit);
        deleteButton = itemView.findViewById(R.id.button_delete);
        detailsButton = itemView.findViewById(R.id.contact_details_button);
        itemView.setOnClickListener(this);
    }

    public void bind(Contact contact) {
        nameTextView.setText(contact.getName());

        // Set click listeners for edit, delete, and details buttons
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactClickListener != null) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Contact contact = contacts.get(position);
                        contactClickListener.onEditClick(contact);
                    }
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactClickListener != null) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Contact contact = contacts.get(position);
                        contactClickListener.onDeleteClick(contact);
                    }
                }
            }
        });
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactClickListener != null) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Contact contact = contacts.get(position);
                        contactClickListener.onDetailsClick(contact);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (contactClickListener != null) {
            int position = getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Contact contact = contacts.get(position);
                contactClickListener.onContactClick(contact);
            }
        }
    }
}
