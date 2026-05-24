package com.api_search.project.excepetion.client;

public class FindByPasswordExcept extends RuntimeException {
    public FindByPasswordExcept(String message) {
        super("Consult HIBP password Fail " + message);
    }
}
