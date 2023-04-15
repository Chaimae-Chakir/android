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
    @GET("/contacts")
    Call<List<Contact>> getAllContacts();

    @GET("/contacts/{id}")
    Call<Contact> getContactById(@Path("id") Long id);

    @POST("/contacts")
    Call<Contact> createContact(@Body Contact contact);

    @PUT("/contacts/{id}")
    Call<Contact> updateContact(@Path("id") Long id, @Body Contact contact);

    @DELETE("/contacts/{id}")
    Call<Void> deleteContact(@Path("id") Long id);
}
