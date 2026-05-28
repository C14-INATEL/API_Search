package com.api_search.project.unitary.exception;

import com.api_search.project.excepetion.UserExcept;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserExceptTest {
    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        UserExcept exception = new UserExcept("not found");

        assertEquals("Error in class User not found", exception.getMessage());
    }

    @Test
    void shouldBeInstanceOfRuntimeException() {
        UserExcept exception = new UserExcept("error");

        assertInstanceOf(RuntimeException.class, exception);
    }
}
