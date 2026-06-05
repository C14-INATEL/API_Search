package com.api_search.project.unitary.service;

import com.api_search.project.entity.User;
import com.api_search.project.excepetion.UserExcept;
import com.api_search.project.repository.UserRepository;
import com.api_search.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("John");
        user.setEmail("john@email.com");
        user.setPassword("plainPassword");
    }

    @Nested
    @DisplayName("save()")
    class Save {

        @Test
        @DisplayName("should hash password and save user")
        void shouldHashPasswordAndSave() throws UserExcept {
            userService.save(user);
            verify(userRepository, times(1)).save(user);
            assertThat(user.getPassword()).isNotEqualTo("plainPassword");
        }

        @Test
        @DisplayName("should throw UserExcept when password is null")
        void shouldThrowWhenPasswordIsNull() {
            user.setPassword(null);

            assertThatThrownBy(() -> userService.save(user))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("PASSWORD IS NULL");
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(userRepository).save(any());

            assertThatThrownBy(() -> userService.save(user))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("hashCrypt()")
    class HashCrypt {

        @Test
        @DisplayName("should return a bcrypt hashed password")
        void shouldReturnHashedPassword() throws UserExcept {
            String hash = userService.hashCrypt("plainPassword");

            assertThat(hash).isNotNull();
            assertThat(hash).isNotEqualTo("plainPassword");
            assertThat(hash).startsWith("$2a$");
        }
    }

    @Nested
    @DisplayName("checkPassword()")
    class CheckPassword {

        @Test
        @DisplayName("should return true when password matches hash")
        void shouldReturnTrueWhenPasswordMatchesHash() throws UserExcept {
            String password = "plainPassword";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            Boolean result = userService.checkPassword(password, hash);

            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("should return false when password does not match hash")
        void shouldReturnFalseWhenPasswordDoesNotMatchHash() throws UserExcept {
            String hash = BCrypt.hashpw("correctPassword", BCrypt.gensalt());

            Boolean result = userService.checkPassword("wrongPassword", hash);

            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("searchUserWithEmailPassword()")
    class SearchUserWithEmailPassword {

        @Test
        @DisplayName("should return user id when credentials are valid")
        void shouldReturnUserIdWhenCredentialsAreValid() throws UserExcept {
            User user = new User();
            user.setId(1);
            user.setEmail("test@email.com");
            user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));

            when(userRepository.findByEmail("test@email.com"))
                    .thenReturn(Optional.of(user));

            Integer result = userService.searchUserWithEmailPassword(
                    "test@email.com",
                    "123456"
            );

            assertThat(result).isEqualTo(1);
        }

        @Test
        @DisplayName("should throw UserExcept when user is not found")
        void shouldThrowUserExceptWhenUserIsNotFound() {
            when(userRepository.findByEmail("test@email.com"))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() ->
                    userService.searchUserWithEmailPassword(
                            "test@email.com",
                            "123456"))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("User not found");
        }

        @Test
        @DisplayName("should throw UserExcept when password is invalid")
        void shouldThrowUserExceptWhenPasswordIsInvalid() {
            User user = new User();
            user.setId(1);
            user.setEmail("test@email.com");
            user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));

            when(userRepository.findByEmail("test@email.com"))
                    .thenReturn(Optional.of(user));

            assertThatThrownBy(() ->
                    userService.searchUserWithEmailPassword(
                            "test@email.com",
                            "wrongPassword"))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("Invalid Password");
        }
    }

    @Nested
    @DisplayName("searchById()")
    class SearchById {

        @Test
        @DisplayName("should return user when id exists")
        void shouldReturnUserWhenFound() throws UserExcept {
            when(userRepository.findById(1)).thenReturn(Optional.of(user));
            assertThat(userService.searchById(1)).isEqualTo(user);
        }

        @Test
        @DisplayName("should return null when id does not exist")
        void shouldReturnNullWhenNotFound() throws UserExcept {
            when(userRepository.findById(99)).thenReturn(Optional.empty());
            assertThat(userService.searchById(99)).isNull();
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            when(userRepository.findById(1)).thenThrow(new RuntimeException("DB error"));

            assertThatThrownBy(() -> userService.searchById(1))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("searchAll()")
    class SearchAll {

        @Test
        @DisplayName("should return all users")
        void shouldReturnAll() throws UserExcept {
            when(userRepository.findAll()).thenReturn(List.of(user));

            assertThat(userService.searchAll())
                    .hasSize(1)
                    .containsExactly(user);
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            when(userRepository.findAll()).thenThrow(new RuntimeException("DB error"));

            assertThatThrownBy(() -> userService.searchAll())
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("existsByid()")
    class ExistsById {

        @Test
        @DisplayName("should return true when user exists")
        void shouldReturnTrueWhenExists() throws UserExcept {
            when(userRepository.existsById(1)).thenReturn(true);
            assertThat(userService.existsByid(1)).isTrue();
        }

        @Test
        @DisplayName("should return false when user does not exist")
        void shouldReturnFalseWhenNotExists() throws UserExcept {
            when(userRepository.existsById(99)).thenReturn(false);
            assertThat(userService.existsByid(99)).isFalse();
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            when(userRepository.existsById(1)).thenThrow(new RuntimeException("DB error"));

            assertThatThrownBy(() -> userService.existsByid(1))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("deleteByid()")
    class DeleteById {

        @Test
        @DisplayName("should delete without throwing exception")
        void shouldDeleteWithoutException() {
            assertThatCode(() -> userService.deleteByid(1)).doesNotThrowAnyException();
            verify(userRepository, times(1)).deleteById(1);
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(userRepository).deleteById(1);

            assertThatThrownBy(() -> userService.deleteByid(1))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("deleteALL()")
    class DeleteAll {

        @Test
        @DisplayName("should delete all without throwing exception")
        void shouldDeleteWithoutException() {
            assertThatCode(() -> userService.deleteALL()).doesNotThrowAnyException();
            verify(userRepository, times(1)).deleteAll();
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(userRepository).deleteAll();

            assertThatThrownBy(() -> userService.deleteALL())
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("update()")
    class Update {

        @Test
        @DisplayName("should update name and email and return saved user")
        void shouldUpdateAndReturnUser() throws UserExcept {
            User request = new User();
            request.setName("Jane");
            request.setEmail("jane@email.com");

            when(userRepository.findById(1)).thenReturn(Optional.of(user));
            when(userRepository.save(user)).thenReturn(user);

            User result = userService.update(1, request);

            assertThat(result.getName()).isEqualTo("Jane");
            assertThat(result.getEmail()).isEqualTo("jane@email.com");
            verify(userRepository, times(1)).save(user);
        }

        @Test
        @DisplayName("should throw UserExcept when user does not exist")
        void shouldThrowWhenUserNotFound() {
            when(userRepository.findById(99)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> userService.update(99, new User()))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("User not exist 99");
        }

        @Test
        @DisplayName("should throw UserExcept when repository throws RuntimeException")
        void shouldThrowUserExceptOnError() {
            when(userRepository.findById(1)).thenThrow(new RuntimeException("DB error"));

            assertThatThrownBy(() -> userService.update(1, new User()))
                    .isInstanceOf(UserExcept.class)
                    .hasMessageContaining("DB error");
        }
    }
}