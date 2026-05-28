package com.api_search.project.unitary.response;

import com.api_search.project.excepetion.FindByPasswordExcept;
import com.api_search.project.response.FindByPasswordResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FindByPasswordTest {
    private final FindByPasswordResponse response = new FindByPasswordResponse();

    @Test
    void shouldReturnLeakedMessageWhenSuffixFound() throws FindByPasswordExcept {
        String body = "ABC123:10\r\nDEF456:5";
        String suffix = "ABC123";

        String result = response.parse(body, suffix);

        assertEquals("Password leaked 10 time", result);
    }

    @Test
    void shouldReturnNotFoundMessageWhenSuffixNotFound() throws FindByPasswordExcept {
        String body = "ABC123:10\r\nDEF456:5";
        String suffix = "ZZZZZZ";

        String result = response.parse(body, suffix);

        assertEquals("Password was not found in leakeds password logs", result);
    }

    @Test
    void shouldBeCaseInsensitive() throws FindByPasswordExcept {
        String body = "abc123:10";
        String suffix = "ABC123";

        String result = response.parse(body, suffix);

        assertEquals("Password leaked 10 time", result);
    }

    @Test
    void shouldThrowFindByPasswordExceptOnInvalidBody() {
        assertThrows(FindByPasswordExcept.class, () -> response.parse(null, "ABC123"));
    }
}
