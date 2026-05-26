package com.api_search.project.unitary.entity;

import com.api_search.project.entity.Accounts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AccountsTest {
    @Test
    void shouldSetAndGetAllFields() {
        Accounts accounts = new Accounts();

        accounts.setId(1);
        accounts.setUserId(10);
        accounts.setEmailMonitored("test@example.com");
        accounts.setNameBreaches("Adobe");
        accounts.setTitle("Adobe");
        accounts.setDomain("adobe.com");
        accounts.setLogoPath("https://logo.com/adobe.png");
        accounts.setAttribution("Adobe Inc");
        accounts.setDescription("Adobe breach 2013");
        accounts.setPwnCount(153000000);
        accounts.setBreachDate(LocalDate.of(2013, 10, 4));
        accounts.setAddedDate(OffsetDateTime.now());
        accounts.setModifiedDate(OffsetDateTime.now());
        accounts.setDataClasses(List.of("Email", "Password"));
        accounts.setVerified(true);
        accounts.setFabricated(false);
        accounts.setSsensitive(false);
        accounts.setRetired(false);
        accounts.setSpamList(false);
        accounts.setMalware(false);
        accounts.setSubscriptionFree(true);
        accounts.setStealerLog(false);

        assertEquals(1, accounts.getId());
        assertEquals(10, accounts.getUserId());
        assertEquals("test@example.com", accounts.getEmailMonitored());
        assertEquals("Adobe", accounts.getNameBreaches());
        assertEquals("Adobe", accounts.getTitle());
        assertEquals("adobe.com", accounts.getDomain());
        assertEquals("https://logo.com/adobe.png", accounts.getLogoPath());
        assertEquals("Adobe Inc", accounts.getAttribution());
        assertEquals("Adobe breach 2013", accounts.getDescription());
        assertEquals(153000000, accounts.getPwnCount());
        assertEquals(LocalDate.of(2013, 10, 4), accounts.getBreachDate());
        assertEquals(List.of("Email", "Password"), accounts.getDataClasses());
        assertTrue(accounts.isVerified());
        assertFalse(accounts.isFabricated());
        assertFalse(accounts.isSsensitive());
        assertFalse(accounts.isRetired());
        assertFalse(accounts.isSpamList());
        assertFalse(accounts.isMalware());
        assertTrue(accounts.isSubscriptionFree());
        assertFalse(accounts.isStealerLog());
    }
}
