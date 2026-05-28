package com.api_search.project.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FindByEmailExcept extends RuntimeException {
    public FindByEmailExcept(String message) {
        super("Error in client FindByEmail: " + message);
    }
}
