package com.api_search.project.entity;

import com.api_search.project.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    @Test
    void shouldSetAndGetAllFields() {
        User user = new User();

        user.setId(1);
        user.setName("John");
        user.setToken("token123");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        user.setDateUpdate(LocalDateTime.of(2024, 1, 2, 10, 0));

        assertEquals(1, user.getId());
        assertEquals("John", user.getName());
        assertEquals("token123", user.getToken());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), user.getDate());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), user.getDateUpdate());
    }

    @Test
    void shouldSetDatesOnPrePersist() {
        User user = new User();
        user.prePersist();

        assertNotNull(user.getDate());
        assertNotNull(user.getDateUpdate());
    }

    @Test
    void shouldUpdateDateOnPreUpdate() {
        User user = new User();
        user.prePersist();

        LocalDateTime before = user.getDateUpdate();
        user.preUpdate();

        assertNotNull(user.getDateUpdate());
    }
}
