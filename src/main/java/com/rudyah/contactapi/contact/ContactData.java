package com.rudyah.contactapi.contact;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ContactData {

    private final String firstName;
    private final String lastName;
    private final String email;
}
