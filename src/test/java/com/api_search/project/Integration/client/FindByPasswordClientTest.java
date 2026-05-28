package com.api_search.project.Integration.client;

import com.api_search.project.client.FindByPasswordClient;
import com.api_search.project.excepetion.FindByPasswordExcept;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FindByPasswordClientTest {
    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    void tearDown() throws IOException{
        mockWebServer.shutdown();
    }

    @Test
    void shouldGenerateCorrectSha1Hash() throws FindByPasswordExcept{
        FindByPasswordClient client = new FindByPasswordClient(
                mockWebServer.url("/").toString(),
                "test-api-key"
        );
        String hash = client.sha1("password");
        assertEquals("5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8", hash);
    }

    @Test
    void shouldThrowFindByPasswordExceptOnInvalidInput(){
        FindByPasswordClient client = new FindByPasswordClient(
                mockWebServer.url("/").toString(),
                "test-api-key"
        );
        assertThrows(FindByPasswordExcept.class, () -> client.sha1(null));
    }
}
