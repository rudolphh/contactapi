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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactEntityConverter contactEntityConverter;

    @Override
    public List<ContactData> getContacts(String keyword) {
        List<Contact> contacts;
        if (keyword != null) {
            contacts = contactRepository.search(keyword);
        } else {
            contacts = contactRepository.findAll();
        }
        return contacts.stream().map(contactEntityConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public Contact addContact(Contact contact) {
        Optional<Contact> contactEmail = contactRepository.findContactByEmail(contact.getEmail());

        contactEmail.ifPresent(log::info);
        if (contactEmail.isPresent()) {
            throw new EmailExistsException("Email already exists");
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

    @Override
    public Contact getContactById(Long contactId) {
        Optional<Contact> contactById= contactRepository.findById(contactId);
        if (contactById.isEmpty()) {
            throw new ContactNotFoundException("Contact with id: " + contactId + " does not exist");
        }
        return contactById.get();
    }

}
