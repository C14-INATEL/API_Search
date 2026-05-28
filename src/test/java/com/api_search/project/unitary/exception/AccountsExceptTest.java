package com.api_search.project.unitary.exception;

import com.api_search.project.excepetion.AccountsExcept;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AccountsExceptTest {
    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        AccountsExcept exception = new AccountsExcept("not found");

        assertEquals("Error in class Accounts: not found", exception.getMessage());
    }

    @Test
    void shouldBeInstanceOfRuntimeException() {
        AccountsExcept exception = new AccountsExcept("error");

        assertInstanceOf(RuntimeException.class, exception);
    }
}
