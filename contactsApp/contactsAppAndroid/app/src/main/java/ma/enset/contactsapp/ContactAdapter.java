package ma.enset.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public void deleteContact(Contact contact) {
        int position = contacts.indexOf(contact);
        if (position != -1) {
            contacts.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts == null ? 0 : contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView emailTextView;
        private TextView jobTextView;
        private TextView numberTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contact_name);
            emailTextView = itemView.findViewById(R.id.contact_email);
            jobTextView = itemView.findViewById(R.id.contact_job);
            numberTextView = itemView.findViewById(R.id.contact_phone);
        }

        public void bind(Contact contact) {
            nameTextView.setText(contact.getName());
            emailTextView.setText(contact.getEmail());
            jobTextView.setText(contact.getJob());
            numberTextView.setText(contact.getNumber());
        }
    }
}

