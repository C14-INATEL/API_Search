package com.api_search.project.unitary.exception;

import com.api_search.project.excepetion.FindByEmailExcept;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindByEmailTest {
    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        FindByEmailExcept exception = new FindByEmailExcept("not found");

        assertEquals("Error in client FindByEmail: not found", exception.getMessage());
    }

    @Test
    void shouldBeInstanceOfRuntimeException() {
        FindByEmailExcept exception = new FindByEmailExcept("error");

        assertInstanceOf(RuntimeException.class, exception);
    }
}
