package ma.enset.contactsappspringboot.repositories;


import ma.enset.contactsappspringboot.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
