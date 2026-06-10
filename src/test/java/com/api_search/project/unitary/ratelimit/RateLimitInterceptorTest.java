package com.api_search.project.unitary.ratelimit;

import com.api_search.project.ratelimit.RateLimitInterceptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class RateLimitInterceptorTest {

    private RateLimitInterceptor interceptor;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        interceptor = new RateLimitInterceptor();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setRemoteAddr("192.168.0.1");
    }

    // Permite requisições dentro do limite
    @Test
    void shouldAllowRequestsWithinLimit() throws Exception {
        for (int i = 0; i < 5; i++) {
            boolean result = interceptor.preHandle(request, response, new Object());
            assertTrue(result, "Requisição " + (i + 1) + " deveria ser permitida");
        }
    }

    // Bloqueia após exceder o limite
    @Test
    void shouldBlockAfterLimitExceeded() throws Exception {
        for (int i = 0; i < 5; i++) {
            interceptor.preHandle(request, response, new Object());
        }

        boolean result = interceptor.preHandle(request, response, new Object());
        assertFalse(result, "6ª requisição deveria ser bloqueada");
    }

    // Retorna 429 ao bloquear
    @Test
    void shouldReturn429WhenBlocked() throws Exception {
        for (int i = 0; i < 5; i++) {
            interceptor.preHandle(request, response, new Object());
        }

        interceptor.preHandle(request, response, new Object());
        assertEquals(429, response.getStatus());
    }

    // Retorna mensagem correta ao bloquear
    @Test
    void shouldReturnCorrectMessageWhenBlocked() throws Exception {
        for (int i = 0; i < 5; i++) {
            interceptor.preHandle(request, response, new Object());
        }

        interceptor.preHandle(request, response, new Object());
        assertEquals("Muitas requisições. Tente novamente daqui alguns minutos", response.getContentAsString());
    }

    // IPs diferentes têm buckets independentes
    @Test
    void shouldHaveIndependentBucketsPerIp() throws Exception {
        request.setRemoteAddr("192.168.0.1");
        for (int i = 0; i < 5; i++) {
            interceptor.preHandle(request, response, new Object());
        }

        MockHttpServletRequest request2 = new MockHttpServletRequest();
        request2.setRemoteAddr("192.168.0.2");
        MockHttpServletResponse response2 = new MockHttpServletResponse();

        boolean result = interceptor.preHandle(request2, response2, new Object());
        assertTrue(result, "IP diferente deveria ter seu próprio bucket");
    }

    // Primeiro request sempre permitido
    @Test
    void shouldAlwaysAllowFirstRequest() throws Exception {
        boolean result = interceptor.preHandle(request, response, new Object());
        assertTrue(result);
        assertEquals(200, response.getStatus());
    }

    // Exatamente 5 requests permitidos
    @Test
    void shouldAllowExactlyFiveRequests() throws Exception {
        int allowed = 0;
        for (int i = 0; i < 10; i++) {
            MockHttpServletResponse res = new MockHttpServletResponse();
            if (interceptor.preHandle(request, res, new Object())) {
                allowed++;
            }
        }
        assertEquals(5, allowed);
    }
}