package com.api_search.project.unitary.controller;

import com.api_search.project.controller.UserController;
import com.api_search.project.entity.User;
import com.api_search.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldSaveUser() throws Exception{
        mockMvc.perform(post("/api-search/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"John\"}"))
                .andExpect(status().isOk());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void shouldsearchById() throws Exception{
        User user = new User();
        user.setId(1);
        when(userService.searchById(1)).thenReturn(user);
        mockMvc.perform(get("/api-search/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        when(userService.searchAll()).thenReturn(List.of(new User(), new User()));

        mockMvc.perform(get("/api-search/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldCheckIfUserExists() throws Exception {
        when(userService.existsByid(1)).thenReturn(true);

        mockMvc.perform(get("/api-search/users/1/exist"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    void shouldReturnUserIdWhenEmailAndPasswordAreValid() throws Exception {

        when(userService.searchUserWithEmailPassword("test@email.com", "123456"))
                .thenReturn(1);

        mockMvc.perform(post("/api-search/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@email.com\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void shouldReturnErrorWhenEmailAndPasswordAreInvalid() throws Exception {

        when(userService.searchUserWithEmailPassword("test@email.com", "wrongPassword"))
                .thenThrow(new RuntimeException("Invalid Password"));

        mockMvc.perform(post("/api-search/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@email.com\",\"password\":\"wrongPassword\"}"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldDeleteUserById() throws Exception {
        mockMvc.perform(delete("/api-search/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteByid(1);
    }

    @Test
    void shouldDeleteAllUsers() throws Exception {
        mockMvc.perform(delete("/api-search/users"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteALL();
    }

    @Test
    void shouldUpdateUser() throws Exception {
        User updated = new User();
        updated.setId(1);

        when(userService.update(eq(1), any(User.class))).thenReturn(updated);

        mockMvc.perform(put("/api-search/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"name\":\"John Updated\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldReturnErrorWhenServiceThrows() throws Exception {
        when(userService.searchById(99)).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(get("/api-search/users/99"))
                .andExpect(status().is5xxServerError());
    }
}
