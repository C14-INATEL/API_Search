package com.api_search.project.unitary.controller;

import com.api_search.project.client.FindByPasswordClient;
import com.api_search.project.controller.FindByPasswordController;
import com.api_search.project.response.FindByPasswordResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FindByPasswordController.class)
public class FindByPasswordTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindByPasswordClient client;

    @MockitoBean
    private FindByPasswordResponse response;

    @Test
    void shouldReturnPasswordCheckResult() throws Exception {
        String hash = "CBFDAC6008F9CAB4083784CBD1874F76618D2A97";
        String suffix = hash.substring(5);
        String body = "C6008F9CAB4083784CBD1874F76618D2A97:10";

        when(client.sha1("password123")).thenReturn(hash);
        when(client.checkPassword("password123")).thenReturn(body);
        when(response.parse(body, suffix)).thenReturn("Password found 10 times!");

        mockMvc.perform(get("/api-search/checkPassword/password")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Password found 10 times!"));
    }

    @Test
    void shouldReturnErrorWhenExceptionThrown() throws Exception {
        when(client.sha1("password123")).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(get("/api-search/checkPassword/password")
                .param("password", "password123"))
                .andExpect(status().isInternalServerError());
    }

}
