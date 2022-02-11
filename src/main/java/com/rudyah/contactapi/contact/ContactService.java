package com.rudyah.contactapi.contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts(String keyword);

    Contact addContact(Contact contact);

    void deleteContact(Long contactId);

    Contact updateContact(Long contactId, Contact contact);

    Contact getContactById(Long contactId);
}
