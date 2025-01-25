package pl.edu.pjwstk.s29748.s29748Bank.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjwstk.s29748.s29748Bank.finances.FinancesService;


import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    AccountStorage accountStorage;

    @InjectMocks
    AccountService accountService;

    @Test
    void shouldFindByAccountID() {
        //given
        int testAccountID = 1;
        when(accountStorage.getAccounts()).thenReturn(List.of(
                new Account(1, 1, 1),
                new Account(2, 2, 2),
                new Account(3, 3, 3),
                new Account(4, 4, 4)));
        //when
        Account account = accountService.findByAccountID(testAccountID);
        //then
        assertThat(account.getAccountID()).isEqualTo(testAccountID);

    }

    @Test
    void shouldWrongAccountID() {
        int testAccountID = 5;
        when(accountStorage.getAccounts()).thenReturn(List.of(
                new Account(1, 1, 1),
                new Account(2, 2, 2),
                new Account(3, 3, 3),
                new Account(4, 4, 4)));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> accountService.findByAccountID(testAccountID))
                .withMessage("Account not found");
    }

    @Test
    void shouldFindByClientID() {
        int testClientID = 1;
        when(accountStorage.getAccounts()).thenReturn(List.of(
                new Account(1, 1, 1),
                new Account(2, 2, 2),
                new Account(3, 3, 3),
                new Account(4, 4, 4)));
        //when
        Account account = accountService.findByClientID(testClientID);
        //then
        assertThat(account.getClientID()).isEqualTo(testClientID);
    }

    @Test
    void shouldWrongClientID() {
        int testClientID = 5;
        when(accountStorage.getAccounts()).thenReturn(List.of(
                new Account(1, 1, 1),
                new Account(2, 2, 2),
                new Account(3, 3, 3),
                new Account(4, 4, 4)));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> accountService.findByClientID(testClientID))
                .withMessage("No account with that client found. Access DENIED");

    }
}