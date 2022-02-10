package com.rudyah.contactapi.error.exceptions;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
