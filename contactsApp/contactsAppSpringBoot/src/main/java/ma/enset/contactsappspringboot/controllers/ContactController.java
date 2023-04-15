package ma.enset.contactsappspringboot.controllers;


import ma.enset.contactsappspringboot.ResourceNotFoundException;
import ma.enset.contactsappspringboot.entities.Contact;
import ma.enset.contactsappspringboot.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
    }

    @PostMapping("")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
        Contact Updatedcontact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));

        Updatedcontact.setName(contact.getName());
        Updatedcontact.setEmail(contact.getEmail());
        Updatedcontact.setJob(contact.getJob());
        Updatedcontact.setNumber(contact.getNumber());
        return contactRepository.save(Updatedcontact);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));

        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }
}

