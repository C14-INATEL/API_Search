package com.api_search.project.unitary.controller;
import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.controller.FindByEmailController;
import com.api_search.project.response.FindByEmailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindByEmailController.class)
public class FindByEmailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private FindByEmailClient findByEmailClient;

    @Test
    void shouldReturnBreachesForEmail() throws Exception {
        FindByEmailResponse response = new FindByEmailResponse();
        response.setName("Adobe");

        when(findByEmailClient.findByEmailResponseFlux("test@example.com"))
                .thenReturn(Flux.just(response));

        mockMvc.perform(get("/api-search/v1/breached/test@example.com"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnEmptyWhenNoBreaches() throws Exception {
        when(findByEmailClient.findByEmailResponseFlux("clean@example.com"))
                .thenReturn(Flux.empty());

        mockMvc.perform(get("/api-search/v1/breached/clean@example.com"))
                .andExpect(status().isOk());
    }
}
