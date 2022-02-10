package com.rudyah.contactapi.contact;

import com.rudyah.contactapi.error.exceptions.ContactNotFoundException;
import com.rudyah.contactapi.error.exceptions.EmailExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getContacts(String keyword) {
        if (keyword != null) {
            return contactRepository.search(keyword);
        }
        return contactRepository.findAll();
    }

    @Override
    public Contact addContact(Contact contact) {
        Optional<Contact> contactEmail = contactRepository.findContactByEmail(contact.getEmail());

        contactEmail.ifPresent(log::info);
        if (contactEmail.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long contactId) {
        boolean exists = contactRepository.existsById(contactId);
        log.info(exists);
        if (!exists) {
            throw new ContactNotFoundException("Contact with id: " + contactId + " does not exist");
        }
        contactRepository.deleteById(contactId);
    }

    @Override
    @Transactional
    public Contact updateContact(Long contactId, Contact contact) {

        Contact currentContact = contactRepository.findById(contactId)
                .orElseThrow(() -> {
                    throw new ContactNotFoundException("Contact not found");
                });

        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String email = contact.getEmail();

        if (firstName != null && !firstName.isEmpty() && !Objects.equals(firstName, currentContact.getFirstName())) {
            currentContact.setFirstName(firstName);
        }

        if (lastName != null && !lastName.isEmpty() && !Objects.equals(lastName, currentContact.getLastName())) {
            currentContact.setLastName(lastName);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(email, currentContact.getEmail())) {
            if (contactRepository.existsByEmail(email)) {
                throw new EmailExistsException("Email already exists");
            }
            currentContact.setEmail(email);
        }

        return currentContact;
    }

}
