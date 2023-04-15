package ma.enset.contactsapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private static final String BASE_URL = "http://192.168.56.1:8082";
    private static ContactService contactService;

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            contactService = retrofit.create(ContactService.class);
        } catch (Exception e) {
            // handle exception, for example:
            Log.e("MyApp", "Error setting up Retrofit: " + e.getMessage());
            Toast.makeText(this, "Unable to connect to server", Toast.LENGTH_LONG).show();
        }

    }

    public static ContactService getContactService() {
        return contactService;
    }
}
