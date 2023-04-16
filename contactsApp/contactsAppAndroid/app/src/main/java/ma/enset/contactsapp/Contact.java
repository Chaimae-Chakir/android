package ma.enset.contactsapp;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String job;

    public Contact( String name, String phone,String email,String job ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.job=job;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getJob() {
        return job;
    }

}
