package com.api_search.project.service;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.User;
import com.api_search.project.repository.AlertRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @InjectMocks
    private AlertService alertService;

    @Mock
    private AlertRepository alertRepository;

    @Test
    @DisplayName("Should to save new alert on database")
    void shouldSave() {
        Alert alert = new Alert();
        alert.setId(1);
        alert.setEmail("igortoledo@gmail.com");
        alert.setLeak("Youtube.com");
        alert.setRisk_level("LOW");

        when(alertRepository.save(alert)).thenReturn(alert);

        // Execution
        alertService.save(alert);

        // Assert
        verify(alertRepository, Mockito.times(1)).save(alert);
    }

    @Test
    @DisplayName("Should to save a object")
    void shouldSaveObject() {
        Alert alert = new Alert();
        alert.setId(1);
        alert.setEmail("igortoledo@gmail.com");
        alert.setLeak("Youtube.com");
        alert.setRisk_level("LOW");

        when(alertRepository.save(alert)).thenReturn(alert);

        // Execution
        Alert savedAlert = alertService.saveObject(alert);

        // Assert
        assertNotNull(savedAlert);
        assertEquals("igortoledo@gmail.com", savedAlert.getEmail());
        assertEquals("Youtube.com", savedAlert.getLeak());
        assertEquals("LOW", savedAlert.getRisk_level());

        verify(alertRepository, Mockito.times(1)).save(alert);
    }


    @Test
    @DisplayName("Should to return a alert existent")
    void shouldSearchAccountById() {
        Integer id = 1;
        Alert alert = new Alert();
        alert.setId(id);
        alert.setEmail("igortoledo@gmail.com");

        when(alertRepository.findById(id)).thenReturn(java.util.Optional.of(alert));

        //Execution
        Alert alertSearched = alertService.searchAccountById(id);

        //Assert
        assertNotNull(alertSearched);
        assertEquals("igortoledo@gmail.com", alertSearched.getEmail());
    }

    @Test
    @DisplayName("Should to return a alert accounts")
    void shouldSearchAccountsByUser() {
        Integer userId = 1;

        Alert alert1 = new Alert();
        Alert alert2 = new Alert();

        alert1.setUserId(userId);
        alert2.setUserId(userId);

        when(alertRepository.findByUserId(userId)).thenReturn(List.of(alert1, alert2));

        // Execution
        List<Alert> result = alertService.searchAccountsByUser(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(alert1));
        assertTrue(result.contains(alert2));
    }

    @Test
    @DisplayName("Should return List of alerts")
    void shouldSearchAll() {
        Alert alert1 = new Alert();
        Alert alert2 = new Alert();
        alert1.setEmail("igortoledo@gmail.com");
        alert2.setEmail("LuanCalabresa.com");

        List<Alert> fakeList = new ArrayList<>();
        fakeList.add(alert1);
        fakeList.add(alert2);

        when(alertRepository.findAll()).thenReturn(fakeList);

        //Execution
        List<Alert> result_fakeList = alertService.searchAll();

        assertNotNull(result_fakeList);
        assertEquals(2, result_fakeList.size());
        assertEquals("igortoledo@gmail.com", result_fakeList.get(0).getEmail());
        assertEquals("LuanCalabresa.com", result_fakeList.get(1).getEmail());
    }

    @Test
    @DisplayName("Should exist alert")
    void shouldExistsByid() {
        Integer id = 2;

        when(alertRepository.findById(id)).thenReturn(java.util.Optional.empty());

        //Execution
        Alert alertSearched = alertService.searchAccountById(id);

        assertNull(alertSearched);
    }

    @Test
    @DisplayName("Should exist User")
    void shouldUserexistsByid() {
        Integer userId = 2;

        when(alertRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        //Execution
        Alert alertSearched = alertService.searchAccountById(userId);

        assertNull(alertSearched);
    }

    @Test
    @DisplayName("Should delete Alert")
    void shouldDeleteByid() {
        Integer id = 1;

        // Execution (Act)
        alertService.deleteByid(id);

        // Assert
        verify(alertRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should delete user Accounts")
    void shouldDeleteAllUserAccounts() {
        Integer userId = 1;

        // Execution (Act)
        alertService.deleteAllUserAccounts(userId);

        // Assert
        verify(alertRepository, Mockito.times(1)).deleteByUserId(userId);
    }

    @Test
    @DisplayName("Should delete all accounts of data")
    void shouldDeleteALL() {
        //Execution
        alertService.deleteALL();

        verify(alertRepository, Mockito.times(1)).deleteAll();
    }
}