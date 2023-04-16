package ma.enset.contactsapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT = "ma.enset.contactsapp.EXTRA_CONTACT";

    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private TextView jobTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactdetailsactivity);

        nameTextView = findViewById(R.id.name_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        emailTextView = findViewById(R.id.email_text_view);
        jobTextView = findViewById(R.id.job_text_view);

        Contact contact = getIntent().getParcelableExtra(EXTRA_CONTACT);
        if (contact != null) {
            nameTextView.setText(contact.getName());
            phoneTextView.setText(contact.getPhone());
            emailTextView.setText(contact.getEmail());
            jobTextView.setText(contact.getJob());
        }
    }
}
