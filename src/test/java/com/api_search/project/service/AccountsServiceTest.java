package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.repository.AccountsRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {

    @InjectMocks
    private AccountsService accountsService;

    @Mock
    private AccountsRepository accountsRepository;

    @Test
    @DisplayName("Should save a new account on database")
    void shouldSave() {
        Accounts account = new Accounts();
        account.setUserId(1);
        account.setAddress("igor@gmail.com");
        account.setDescription("linkedin");
        account.setStatus("Low");
        account.setPassword_hash("sdfsdfsdfsdf");

        when(accountsRepository.save(account)).thenReturn(account);

        // Execution
        accountsService.save(account);

        verify(accountsRepository, Mockito.times(1)).save(account);
    }

    @Test
    @Disabled("Ignoring to implementation CI/CD") //###################################################################
    @DisplayName("Should to save a object")
    void shouldSaveObject() {
        Accounts account = new Accounts();
        account.setUserId(1);
        account.setAddress("igor@gmail.com");
        account.setDescription("linkedin");
        account.setStatus("Low");
        account.setPassword_hash("sdfsdfsdfsdf");

        when(accountsRepository.save(account)).thenReturn(account);

        // Execution
        Accounts savedAccount = accountsService.saveObject(account);

        assertNotNull(savedAccount);
        assertEquals(1, savedAccount.getUserId());
        assertEquals("igor@gmail.com", savedAccount.getAddress());
        assertEquals("linkedin", savedAccount.getDescription());
        assertEquals("Low", savedAccount.getStatus());
        assertEquals("sdfsdfsdfsdf", savedAccount.getPassword_hash());

        verify(accountsRepository, Mockito.times(1)).save(account);
    }

    @Test
    @DisplayName("Should to return a user accounts")
    void shouldSearchAccountsByUser() {
        Integer userId = 1;

        Accounts account1 = new Accounts();
        Accounts account2 = new Accounts();

        account1.setUserId(userId);
        account2.setUserId(userId);

        when(accountsRepository.findByUserId(userId)).thenReturn(List.of(account1, account2));

        // Execution
        List<Accounts> result = accountsService.searchAccountsByUser(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(account1));
        assertTrue(result.contains(account2));
    }

    @Test
    @DisplayName("Should Delete All User Accounts")
    void shouldDeleteAllUserAccounts() {
        Integer userId = 1;

        // Execution (Act)
        accountsService.deleteAllUserAccounts(userId);

        // Assert
        verify(accountsRepository, Mockito.times(1)).deleteByUserId(userId);
    }

    @Test
    @DisplayName("Should Search Account By ID")
    void shouldSearchById() {
        Integer id = 1;
        Accounts account = new Accounts();
        account.setId(id);
        account.setAddress("luan@gmail.com");

        when(accountsRepository.findById(id)).thenReturn(java.util.Optional.of(account));

        // Execution
        Accounts accountSearched = accountsService.searchById(id);

        // Assert
        assertNotNull(accountSearched);
        assertEquals("luan@gmail.com",accountSearched.getAddress());

    }

    @Test
    @DisplayName("Should search All Accounts")
    void shouldSearchAll() {
        Accounts account1 = new Accounts();
        Accounts account2 = new Accounts();
        account1.setAddress("igortoledo@gmail.com");
        account2.setAddress("LuanCalabresa.com");

        List<Accounts> fakeList = new ArrayList<>();
        fakeList.add(account1);
        fakeList.add(account2);

        when(accountsRepository.findAll()).thenReturn(fakeList);

        //Execution
        List<Accounts> result_fakeList = accountsService.searchAll();

        assertNotNull(result_fakeList);
        assertEquals(2, result_fakeList.size());
        assertEquals("igortoledo@gmail.com", result_fakeList.get(0).getAddress());
        assertEquals("LuanCalabresa.com", result_fakeList.get(1).getAddress());
    }

    @Test
    @DisplayName("Should exist account with id")
    void shouldExistsByid() {
        Integer id = 2;

        when(accountsRepository.findById(id)).thenReturn(java.util.Optional.empty());

        //Execution
        Accounts alertSearched = accountsService.searchById(id);

        assertNull(alertSearched);
    }

    @Test
    @DisplayName("Should delete by ID")
    void shouldDeleteByid() {
        Integer id = 1;

        // Execution (Act)
        accountsService.deleteByid(id);

        // Assert
        verify(accountsRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should delete all accounts")
    void shouldDeleteALL() {
        Integer userId = 1;

        // Execution (Act)
        accountsService.deleteAllUserAccounts(userId);

        // Assert
        verify(accountsRepository, Mockito.times(1)).deleteByUserId(userId);
    }
}