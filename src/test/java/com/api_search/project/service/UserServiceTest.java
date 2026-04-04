// AAA (Arrange, Act, Assert) ## Very important to build test
package com.api_search.project.service;

import com.api_search.project.dto.UserDashboardDTO;
import com.api_search.project.entity.User;
import com.api_search.project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should to save new user in database")
    void shouldSave() {
        // Arrange
        User user = new User();
        user.setName("Luan");
        user.setEmail("luanpierre@gmail.com");
        user.setPassword("test of unit password");
        user.setToken("LP1234");

        when(userRepository.save(user)).thenReturn(user);

        // Execution (Act)
        userService.save(user);

        // Assert
        verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    @DisplayName("Should to save a object")
    void shouldSaveObject() {
        // Arrange
        User user = new User();
        user.setName("Luan");
        user.setEmail("luanpierre@gmail.com");
        user.setPassword("test of unit password");
        user.setToken("LP1234");

        when(userRepository.save(user)).thenReturn(user);

        // Execution (Act)
        User savedUser = userService.saveObject(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("Luan", savedUser.getName());
        assertEquals("luanpierre@gmail.com", savedUser.getEmail());
        assertEquals("test of unit password", savedUser.getPassword());
        assertEquals("LP1234", savedUser.getToken());

        verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test
    @DisplayName("Should to return a user existent")
    void shouldSearchById() {
        // Arrange
        Integer id = 1;
        User user = new User();
        user.setId(id);
        user.setName("Luan");

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

        // Execution (Act)
        User userSearched = userService.searchById(id);

        // Assert
        assertNotNull(userSearched);
        assertEquals("Luan",userSearched.getName());
    }

    @Test
    void shouldSearchAll() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        user1.setName("Luan");
        user2.setName("Igor");

        List<User> fakeList = new ArrayList<>();
        fakeList.add(user1);
        fakeList.add(user2);

        when(userRepository.findAll()).thenReturn(fakeList);

        // Execution (Act)
        List<User> result_fakeList = userService.searchAll();

        // Assert
        assertNotNull(result_fakeList);
        assertEquals(2, result_fakeList.size());
        assertEquals("Luan", result_fakeList.get(0).getName());
        assertEquals("Igor", result_fakeList.get(1).getName());

    }

    @Test
    @DisplayName("Should verify if the user exist")
    void shouldExistsByid() {
        // Arrange
        Integer id = 19;

        when(userRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Execution (Act)
        User userSearched = userService.searchById(id);

        // Assert
        assertNull(userSearched);
    }

    @Test
    @DisplayName("Should delete an user by id")
    void shouldDeleteByid() {
        // Arrange
        Integer id = 1;

        // Execution (Act)
        userService.deleteByid(id);

        // Assert
        verify(userRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should delete all user of data")
    void shouldDeleteALL() {
        // Execution (Act)
        userService.deleteALL();
        // Assert
        verify(userRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    void shouldFetchUserDashboard() {
        // Arrange
        UserDashboardDTO dto = mock(UserDashboardDTO.class);
        when(dto.getUsuario()).thenReturn("João");

        when(userRepository.buscarDashboard(20))
                .thenReturn(List.of(dto));

        // Act
        List<UserDashboardDTO> result = userService.getDashboard(20);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals("João", result.get(0).getUsuario());
        verify(userRepository).buscarDashboard(20);
    }
}