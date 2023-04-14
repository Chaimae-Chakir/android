package com.example.contactsapp.repositories;

import com.example.contactsapp.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
