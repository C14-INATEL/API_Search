package com.api_search.project.unitary.exception;

import com.api_search.project.excepetion.FindByPasswordExcept;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinfByPasswordExceptTest {
    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        FindByPasswordExcept exception = new FindByPasswordExcept("timeout");

        assertEquals("Consult HIBP password Fail timeout", exception.getMessage());
    }

    @Test
    void shouldBeInstanceOfRuntimeException() {
        FindByPasswordExcept exception = new FindByPasswordExcept("error");

        assertInstanceOf(RuntimeException.class, exception);
    }
}

