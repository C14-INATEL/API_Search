package com.api_search.project.unitary.controller;

import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.controller.AccountsController;
import com.api_search.project.entity.Accounts;
import com.api_search.project.response.FindByEmailResponse;
import com.api_search.project.service.AccountsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountsService accountsService;

    @MockitoBean
    private FindByEmailClient findByEmailClient;

    @Test
    void shouldReturnAccountsById() throws Exception {
        when(accountsService.searchById(1)).thenReturn(null);

        mockMvc.perform(get("/api-search/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCheckIfAccountExists() throws Exception {
        when(accountsService.existsByid(1)).thenReturn(true);

        mockMvc.perform(get("/api-search//accounts/1/exist"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllAccounts() throws Exception {
        when(accountsService.searchAll()).thenReturn(java.util.List.of());

        mockMvc.perform(get("/api-search/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveAccount() throws Exception {
        mockMvc.perform(post("/api-search/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk());

        verify(accountsService, times(1)).save(any(Accounts.class));
    }

    @Test
    void shouldReturnAccountsByUserId() throws Exception {
        when(accountsService.searchAccountsByUser(1)).thenReturn(List.of());

        mockMvc.perform(get("/api-search/accounts/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteById() throws Exception {
        mockMvc.perform(delete("/api-search/accounts/1"))
                .andExpect(status().isOk());

        verify(accountsService, times(1)).deleteByid(1);
    }

    @Test
    void shouldDeleteAllUserAccounts() throws Exception {
        mockMvc.perform(delete("/api-search/accounts/user/1"))
                .andExpect(status().isOk());

        verify(accountsService, times(1)).deleteAllUserAccounts(1);
    }

    @Test
    void shouldDeleteAll() throws Exception {
        mockMvc.perform(delete("/api-search/accounts"))
                .andExpect(status().isOk());

        verify(accountsService, times(1)).deleteALL();
    }

    @Test
    void shouldMonitorAccount() throws Exception {
        when(findByEmailClient.findByEmailResponseFlux("test@example.com"))
                .thenReturn(Flux.just(new FindByEmailResponse()));

        mockMvc.perform(post("/api-search/accounts/accountMonitored/1/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account added successfully"));

        verify(accountsService, times(1))
                .saveFromResponseEmailWebClientHIBP(any(), eq("test@example.com"), eq(1));
    }
}


