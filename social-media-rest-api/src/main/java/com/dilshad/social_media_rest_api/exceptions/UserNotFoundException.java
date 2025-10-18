package com.dilshad.social_media_rest_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
