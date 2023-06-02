package com.example.contacts;

public class User {
    private Long id;
    String name, phone_profile, email,profession;
    String image;

    public User() {
    }

    public User(Long id ,String name, String phone_profile, String email, String profession) {
        this.id=id;
        this.name = name;
        this.phone_profile = phone_profile;
        this.email = email;
        this.profession= profession;
    }

    public User(Long id, String name, String phone_profile, String email, String profession, String image) {
        this.id = id;
        this.name = name;
        this.phone_profile = phone_profile;
        this.email = email;
        this.profession = profession;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_profile() {
        return phone_profile;
    }

    public void setPhone_profile(String phone_profile) {
        this.phone_profile = phone_profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
