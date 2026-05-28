package com.api_search.project.Integration.client;

import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.response.FindByEmailResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FindByEmailClientTest {
    private MockWebServer mockWebServer;
    private FindByEmailClient client;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        client = new FindByEmailClient(
                mockWebServer.url("/").toString(),
                "test-api-key"
        );
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldReturnBreachesForValidEmail(){
        String mockBody = """
                [{"Name":"Adobe","Title":"Adobe","Domain":"adobe.com","BreachDate":"2013-10-04"}]
                """;
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockBody)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
        );

        Flux<FindByEmailResponse> result = client.findByEmailResponseFlux("test@example.com");

        StepVerifier.create(result)
                .expectNextMatches(r -> r.getName().equals("Adobe"))
                .verifyComplete();
    }

    @Test
    void shouldThrowOnServerError(){
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        StepVerifier.create(client.findByEmailResponseFlux("test@example.com"))
                .expectErrorMatches(e -> e instanceof RuntimeException
                        && e.getMessage().equals("Error 5xx: server not found"))
                .verify();
    }

    @Test
    void shouldThrowOnClientError() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        StepVerifier.create(client.findByEmailResponseFlux("test@example.com"))
                .expectErrorMatches(e -> e instanceof RuntimeException
                        && e.getMessage().equals("Verify the parameters "))
                .verify();
    }
}
