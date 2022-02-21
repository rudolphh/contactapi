package com.rudyah.contactapi.response.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse<T> extends CustomResponse{
    T data;

    public ApiResponse(Boolean success, HttpStatus status, String message) {
        super(success, status, message);
    }

    public ApiResponse(Boolean success, HttpStatus status, String message, T data) {
        super(success, status, message);
        this.data = data;
    }
}
