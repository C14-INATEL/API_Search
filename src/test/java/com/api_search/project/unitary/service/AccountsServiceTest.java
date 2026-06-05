package com.api_search.project.unitary.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.excepetion.AccountsExcept;
import com.api_search.project.repository.AccountsRepository;
import com.api_search.project.response.FindByEmailResponse;
import com.api_search.project.service.AccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountsService")
public class AccountsServiceTest {
    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountsService accountsService;

    private Accounts account;

    @BeforeEach
    void setUp() {
        account = new Accounts();
        account.setUserId(1);
    }

    @Nested
    @DisplayName("save()")
    class Save {

        @Test
        @DisplayName("should delegate to repository")
        void shouldDelegateToRepository() {
            accountsService.save(account);
            verify(accountsRepository, times(1)).save(account);
        }
    }

    @Nested
    @DisplayName("searchAccountsByUser()")
    class SearchAccountsByUser {

        @Test
        @DisplayName("should return account list for user")
        void shouldReturnAccountList() {
            when(accountsRepository.findByUserId(1)).thenReturn(List.of(account));
            List<Accounts> result = accountsService.searchAccountsByUser(1);
            assertThat(result).containsExactly(account);
        }

        @Test
        @DisplayName("should return empty list when user has no accounts")
        void shouldReturnEmptyList() {
            when(accountsRepository.findByUserId(99)).thenReturn(Collections.emptyList());
            List<Accounts> result = accountsService.searchAccountsByUser(99);
            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("deleteAllUserAccounts()")
    class DeleteAllUserAccounts {

        @Test
        @DisplayName("should delete without throwing exception")
        void shouldDeleteWithoutException() {
            assertThatCode(() -> accountsService.deleteAllUserAccounts(1)).doesNotThrowAnyException();
            verify(accountsRepository, times(1)).deleteByUserId(1);
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(accountsRepository).deleteByUserId(1);
            assertThatThrownBy(() -> accountsService.deleteAllUserAccounts(1))
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("searchById()")
    class SearchById {

        @Test
        @DisplayName("should return account when id exists")
        void shouldReturnAccountWhenFound() throws AccountsExcept {
            when(accountsRepository.findById(1)).thenReturn(Optional.of(account));
            assertThat(accountsService.searchById(1)).isEqualTo(account);
        }

        @Test
        @DisplayName("should return null when id does not exist")
        void shouldReturnNullWhenNotFound() throws AccountsExcept {
            when(accountsRepository.findById(99)).thenReturn(Optional.empty());
            assertThat(accountsService.searchById(99)).isNull();
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            when(accountsRepository.findById(1)).thenThrow(new RuntimeException("DB error"));
            assertThatThrownBy(() -> accountsService.searchById(1))
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("searchAll()")
    class SearchAll {

        @Test
        @DisplayName("should return all accounts")
        void shouldReturnAll() throws AccountsExcept {
            when(accountsRepository.findAll()).thenReturn(List.of(account));
            assertThat(accountsService.searchAll()).hasSize(1).containsExactly(account);
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            when(accountsRepository.findAll()).thenThrow(new RuntimeException("DB error"));
            assertThatThrownBy(() -> accountsService.searchAll())
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("existsByid()")
    class ExistsById {

        @Test
        @DisplayName("should return true when account exists")
        void shouldReturnTrueWhenExists() throws AccountsExcept {
            when(accountsRepository.existsById(1)).thenReturn(true);
            assertThat(accountsService.existsByid(1)).isTrue();
        }

        @Test
        @DisplayName("should return false when account does not exist")
        void shouldReturnFalseWhenNotExists() throws AccountsExcept {
            when(accountsRepository.existsById(99)).thenReturn(false);
            assertThat(accountsService.existsByid(99)).isFalse();
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            when(accountsRepository.existsById(1)).thenThrow(new RuntimeException("DB error"));
            assertThatThrownBy(() -> accountsService.existsByid(1))
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("deleteByid()")
    class DeleteById {

        @Test
        @DisplayName("should delete without throwing exception")
        void shouldDeleteWithoutException() {
            assertThatCode(() -> accountsService.deleteByid(1)).doesNotThrowAnyException();
            verify(accountsRepository, times(1)).deleteById(1);
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(accountsRepository).deleteById(1);
            assertThatThrownBy(() -> accountsService.deleteByid(1))
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("deleteALL()")
    class DeleteAll {

        @Test
        @DisplayName("should delete all without throwing exception")
        void shouldDeleteWithoutException() {
            assertThatCode(() -> accountsService.deleteALL()).doesNotThrowAnyException();
            verify(accountsRepository, times(1)).deleteAll();
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(accountsRepository).deleteAll();
            assertThatThrownBy(() -> accountsService.deleteALL())
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }
    }

    @Nested
    @DisplayName("saveFromResponseEmailWebClientHIBP()")
    class SaveFromResponseEmailWebClientHIBP {

        private FindByEmailResponse buildDto() {
            FindByEmailResponse dto = new FindByEmailResponse();
            dto.setName("Adobe");
            dto.setTitle("Adobe");
            dto.setDomain("adobe.com");
            dto.setPwnCount(152445165);
            dto.setDescription("In October 2013...");
            dto.setLogoPath("https://haveibeenpwned.com/Content/Images/PwnedLogos/Adobe.png");
            dto.setBreachDate(LocalDate.parse("2013-10-04"));
            dto.setAddedDate(OffsetDateTime.parse("2013-12-04T00:00:00Z"));
            dto.setModifiedDate(OffsetDateTime.parse("2022-05-15T23:52:49Z"));
            dto.setDataClasses(List.of("Email addresses", "Password hints"));
            dto.setVerified(true);
            dto.setFabricated(false);
            dto.setSensitive(false);
            dto.setRetired(false);
            dto.setSpamList(false);
            dto.setMalware(false);
            dto.setStealerLog(false);
            dto.setSubscriptionFree(false);
            dto.setAttribution(null);
            return dto;
        }

        @Test
        @DisplayName("should map all DTO fields to entity and save")
        void shouldMapAllFieldsAndSave() throws AccountsExcept {
            FindByEmailResponse dto = buildDto();
            ArgumentCaptor<Accounts> captor = ArgumentCaptor.forClass(Accounts.class);

            accountsService.saveFromResponseEmailWebClientHIBP(dto, "test@adobe.com", 7);

            verify(accountsRepository).save(captor.capture());
            Accounts saved = captor.getValue();

            assertThat(saved.getUserId()).isEqualTo(7);
            assertThat(saved.getEmailMonitored()).isEqualTo("test@adobe.com");
            assertThat(saved.getNameBreaches()).isEqualTo("Adobe");
            assertThat(saved.getTitle()).isEqualTo("Adobe");
            assertThat(saved.getDomain()).isEqualTo("adobe.com");
            assertThat(saved.getPwnCount()).isEqualTo(152445165);
            assertThat(saved.isVerified()).isTrue();
            assertThat(saved.isFabricated()).isFalse();
            assertThat(saved.isSsensitive()).isFalse();
            assertThat(saved.isRetired()).isFalse();
            assertThat(saved.isSpamList()).isFalse();
            assertThat(saved.isMalware()).isFalse();
            assertThat(saved.isStealerLog()).isFalse();
            assertThat(saved.isSubscriptionFree()).isFalse();
            assertThat(saved.getDataClasses()).containsExactly("Email addresses", "Password hints");
        }

        @Test
        @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
        void shouldThrowAccountsExceptOnError() {
            doThrow(new RuntimeException("DB error")).when(accountsRepository).save(any());
            assertThatThrownBy(() ->
                    accountsService.saveFromResponseEmailWebClientHIBP(buildDto(), "test@adobe.com", 7))
                    .isInstanceOf(AccountsExcept.class)
                    .hasMessageContaining("DB error");
        }

        @Nested
        @DisplayName("deleteByEmail()")
        class DeleteByEmail {

            @Test
            @DisplayName("should delete without throwing exception")
            void shouldDeleteWithoutException() {
                assertThatCode(() -> accountsService.deleteByEmail("test@example.com")).doesNotThrowAnyException();
                verify(accountsRepository, times(1)).deleteByEmail("test@example.com");
            }

            @Test
            @DisplayName("should throw AccountsExcept when repository throws RuntimeException")
            void shouldThrowAccountsExceptOnError() {
                doThrow(new RuntimeException("DB error")).when(accountsRepository).deleteByEmail("test@example.com");
                assertThatThrownBy(() -> accountsService.deleteByEmail("test@example.com"))
                        .isInstanceOf(AccountsExcept.class)
                        .hasMessageContaining("DB error");
            }
        }
    }
}
