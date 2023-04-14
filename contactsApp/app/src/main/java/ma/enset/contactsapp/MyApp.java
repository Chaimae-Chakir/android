package ma.enset.contactsapp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private static final String BASE_URL = "http://192.168.0.102:8082";
    private static ContactService contactService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        contactService = retrofit.create(ContactService.class);

    }

    public static ContactService getContactService() {
        return contactService;
    }
}
