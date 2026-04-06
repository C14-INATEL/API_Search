package com.api_search.project.service;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.Leak;
import com.api_search.project.repository.LeakRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeakServiceTest {

    @InjectMocks
    private LeakService leakService;

    @Mock
    private LeakRepository leakRepository;

    @Test
    @DisplayName("Should save Leak on database")
    void shouldSave() {
        Leak leak = new Leak();
        leak.setAccountMonitored("luanCalabresa@gmail.com");
        leak.setRegister(new BigInteger("123456789012345678901234567890"));

        when(leakRepository.save(leak)).thenReturn(leak);

        // Execution
        leakService.save(leak);

        verify(leakRepository, Mockito.times(1)).save(leak);
    }

    @Test
    @DisplayName("Should save leak object")
    void shouldSaveObject() {
        Leak leak = new Leak();
        leak.setAccountMonitored("luanCalabresa@gmail.com");
        leak.setRegister(new BigInteger("123456789012345678901234567890"));

        when(leakRepository.save(leak)).thenReturn(leak);

        // Execution
        Leak savedLeak = leakService.saveObject(leak);

        assertNotNull(savedLeak);
        assertEquals("luanCalabresa@gmail.com", savedLeak.getAccountMonitored());
        assertEquals(new BigInteger("123456789012345678901234567890"), savedLeak.getRegister());

        verify(leakRepository, Mockito.times(1)).save(leak);
    }

    @Test
    @DisplayName("Should find Leak with id n")
    void shouldSearchById() {
        Integer id = 1;
        Leak leak = new Leak();
        leak.setId(id);
        leak.setAccountMonitored("igortoledo@gmail.com");

        when(leakRepository.findById(id)).thenReturn(java.util.Optional.of(leak));

        //Execution
        Leak leakSearched = leakService.searchById(id);

        //Assert
        assertNotNull(leakSearched);
        assertEquals("igortoledo@gmail.com", leakSearched.getAccountMonitored());
    }

    @Test
    @DisplayName("Should show all Leaks")
    void shouldSearchAll() {
        Leak Leak1 = new Leak();
        Leak Leak2 = new Leak();
        Leak1.setAccountMonitored("igortoledo@gmail.com");
        Leak2.setAccountMonitored("LuanCalabresa.com");

        List<Leak> fakeList = new ArrayList<>();
        fakeList.add(Leak1);
        fakeList.add(Leak2);

        when(leakRepository.findAll()).thenReturn(fakeList);

        //Execution
        List<Leak> result_fakeList = leakService.searchAll();

        assertNotNull(result_fakeList);
        assertEquals(2, result_fakeList.size());
        assertEquals("igortoledo@gmail.com", result_fakeList.get(0).getAccountMonitored());
        assertEquals("LuanCalabresa.com", result_fakeList.get(1).getAccountMonitored());
    }

    @Test
    @DisplayName("Should exist Leak with id")
    void shouldExistsByid() {
        Integer id = 2;

        when(leakRepository.findById(id)).thenReturn(java.util.Optional.empty());

        //Execution
        Leak leakSearched = leakService.searchById(id);

        assertNull(leakSearched);
    }

    @Test
    @DisplayName("Should delete a leak By ID")
    void shouldDeleteByid() {
        Integer id = 1;

        // Execution
        leakService.deleteByid(id);

        // Assert
        verify(leakRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldDeleteALL() {
        //Execution
        leakService.deleteALL();

        verify(leakRepository, Mockito.times(1)).deleteAll();
    }
}