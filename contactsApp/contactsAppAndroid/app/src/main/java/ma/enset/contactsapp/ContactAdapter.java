package ma.enset.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<Contact> contacts;
    private ContactClickListener contactClickListener;

    public ContactAdapter(List<Contact> contacts, ContactClickListener contactClickListener) {
        this.contacts = contacts;
        this.contactClickListener = contactClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view, contacts, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface ContactClickListener {
        void onContactClick(Contact contact);

        void onEditClick(Contact contact);

        void onDeleteClick(Contact contact);

        void onDetailsClick(Contact contact);
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public void setContactClickListener(ContactClickListener contactClickListener) {
        this.contactClickListener = contactClickListener;
    }
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        notifyDataSetChanged();
    }

}

