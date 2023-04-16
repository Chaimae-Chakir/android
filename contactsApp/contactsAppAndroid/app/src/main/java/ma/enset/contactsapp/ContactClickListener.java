package ma.enset.contactsapp;

public interface ContactClickListener {
    void onContactClick(Contact contact);
    void onEditClick(Contact contact);
    void onDeleteClick(Contact contact);
    void onDetailsClick(Contact contact);
}
