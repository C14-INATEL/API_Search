package com.api_search.project.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FindByPasswordExcept extends RuntimeException {
    public FindByPasswordExcept(String message) {
        super("Consult HIBP password Fail " + message);
    }
}
