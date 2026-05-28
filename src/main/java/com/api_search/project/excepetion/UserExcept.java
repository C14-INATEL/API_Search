package com.api_search.project.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserExcept extends RuntimeException {
    public UserExcept(String message) {
        super("Error in class User " + message);
    }
}
