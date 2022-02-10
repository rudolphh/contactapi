package com.rudyah.contactapi.contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts();
    Contact addContact(Contact contact);
    void deleteContact(Long contactId);
    Contact updateContact(Long contactId, Contact contact);
}
