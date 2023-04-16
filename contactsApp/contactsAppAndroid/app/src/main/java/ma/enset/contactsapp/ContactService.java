package ma.enset.contactsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {
    @POST("contacts")
    Call<Contact> createContact(@Body Contact contact);
    @GET("contacts")
    Call<List<Contact>> getAllContacts();

    @GET("contacts/{id}")
    Call<Contact> getContactById(@Path("id") int id);

    @PUT("contacts/{id}")
    Call<Void> updateContact(@Path("id") int id, @Body Contact contact);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") int id);

}

