package com.rudyah.contactapi.response.models;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationErrorResponse extends CustomResponse {

    private List<String> errors;

    public ValidationErrorResponse(HttpStatus status, String message, List<String> errors) {
        super(false, status, message);
        this.errors = errors;
    }

    public ValidationErrorResponse(HttpStatus status, String message, String errors) {
        super(false, status, message);
        this.errors = List.of(errors);
    }

}