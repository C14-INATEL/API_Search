package com.api_search.project.unitary.response;

import com.api_search.project.response.FindByEmailResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindByEmailResponseTest {
    @Test
    void shouldSetAndGetAllFields() {
        FindByEmailResponse response = new FindByEmailResponse();

        response.setName("Adobe");
        response.setTitle("Adobe");
        response.setDomain("adobe.com");
        response.setLogoPath("https://logo.com/adobe.png");
        response.setAttribution("Adobe Inc");
        response.setDescription("Adobe breach 2013");
        response.setPwnCount(153000000);
        response.setBreachDate(LocalDate.of(2013, 10, 4));
        response.setAddedDate(OffsetDateTime.now());
        response.setModifiedDate(OffsetDateTime.now());
        response.setDataClasses(List.of("Email", "Password"));
        response.setVerified(true);
        response.setFabricated(false);
        response.setSensitive(false);
        response.setRetired(false);
        response.setSpamList(false);
        response.setMalware(false);
        response.setSubscriptionFree(true);
        response.setStealerLog(false);

        assertEquals("Adobe", response.getName());
        assertEquals("Adobe", response.getTitle());
        assertEquals("adobe.com", response.getDomain());
        assertEquals("https://logo.com/adobe.png", response.getLogoPath());
        assertEquals("Adobe Inc", response.getAttribution());
        assertEquals("Adobe breach 2013", response.getDescription());
        assertEquals(153000000, response.getPwnCount());
        assertEquals(LocalDate.of(2013, 10, 4), response.getBreachDate());
        assertEquals(List.of("Email", "Password"), response.getDataClasses());
        assertTrue(response.isVerified());
        assertFalse(response.isFabricated());
        assertFalse(response.isSensitive());
        assertFalse(response.isRetired());
        assertFalse(response.isSpamList());
        assertFalse(response.isMalware());
        assertTrue(response.isSubscriptionFree());
        assertFalse(response.isStealerLog());
    }
}
