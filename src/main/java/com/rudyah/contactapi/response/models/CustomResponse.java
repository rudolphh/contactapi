package com.rudyah.contactapi.response.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public abstract class CustomResponse {
    Boolean success;
    HttpStatus status;
    LocalDateTime timestamp;
    String message;

    public CustomResponse(Boolean success, HttpStatus status, String message) {
        this.success = success;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
