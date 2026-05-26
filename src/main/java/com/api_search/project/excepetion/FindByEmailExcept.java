package com.api_search.project.excepetion;

public class FindByEmailExcept extends RuntimeException {
    public FindByEmailExcept(String message) {
        super(message);
    }
}
