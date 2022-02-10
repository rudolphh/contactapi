package com.rudyah.contactapi.error.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String errorMessage){
        super(errorMessage);
    }
}
