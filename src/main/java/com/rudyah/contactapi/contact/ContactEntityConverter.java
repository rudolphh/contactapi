package com.rudyah.contactapi.contact;

import org.springframework.stereotype.Component;

@Component
public class ContactEntityConverter {
    public ContactData toResponse(Contact contact) {
        return new ContactData(
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail());
    }
}