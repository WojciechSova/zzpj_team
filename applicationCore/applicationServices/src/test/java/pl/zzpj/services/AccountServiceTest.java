package pl.zzpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.UserCredentials;
import pl.zzpj.model.UserPrincipal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountCRUDPort accountCRUDPort;
    @InjectMocks
    AccountService accountService;

    private List<Account> accounts;

    @Spy
    Account account1 = new Account();
    @Spy
    Account account2 = new Account();
    @Spy
    Account account3 = new Account();

    private final String login1 = "login1";
    private final String login2 = "login2";
    private final String login3 = "login3";
    private final String password1 = "Password1";
    private final String firstName3 = "Name3";
    private final String lastName3 = "Surname3";
    private final Currency currency3 = Currency.CHF;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);

        accounts = new ArrayList<>();
        accounts.addAll(Arrays.asList(account1, account2));

        when(account1.getLogin()).thenReturn(login1);
        when(account3.getLogin()).thenReturn(login3);
    }

    @Test
    void addAccount() {
        doAnswer(invocationOnMock -> {
            accounts.add(account3);
            return null;
        }).when(accountCRUDPort).addAccount(account3);

        accountService.addAccount(account3);

        assertEquals(3, accounts.size());
        assertEquals(login3, accounts.get(accounts.size() - 1).getLogin());
    }

    @Test
    void removeAccount() {
        doAnswer(invocationOnMock -> {
            accounts.remove(account2);
            return null;
        }).when(accountCRUDPort).removeAccount(account2);

        accountService.removeAccount(account2);

        assertEquals(1, accounts.size());
    }

    @Test
    void updateAccount() {
        when(account3.getFirstName()).thenReturn(firstName3);
        when(account3.getLastName()).thenReturn(lastName3);
        when(account3.getCurrency()).thenReturn(currency3);

        doAnswer(invocationOnMock -> {
            account1.setFirstName(account3.getFirstName());
            account1.setLastName(account3.getLastName());
            account1.setCurrency(account3.getCurrency());
            return null;
        }).when(accountCRUDPort).updateAccount(login1, account3);

        accountService.updateAccount(login1, account3);

        assertAll(
                () -> assertEquals(firstName3, account1.getFirstName()),
                () -> assertEquals(lastName3, account1.getLastName()),
                () -> assertEquals(currency3, account1.getCurrency())
        );
    }

    @Test
    void findByLogin() {
        when(accountCRUDPort.findByLogin(login1)).thenReturn(account1);

        assertEquals(account1.getLogin(), accountService.findByLogin(login1).getLogin());
    }

    @Test
    void findAll() {
        when(accountCRUDPort.findAll()).thenReturn(accounts);

        assertEquals(accounts.hashCode(), accountService.findAll().hashCode());
    }

    @Test
    void loadUserByUsername() {
        when(accountCRUDPort.findByLogin(login1)).thenReturn(account1);

        assertEquals(new UserPrincipal(account1).getUsername(), accountService.loadUserByUsername(login1).getUsername());
    }

    @Test
    void loadUserByUsernameException() {
        when(accountCRUDPort.findByLogin(login1)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername(login1));
    }

    @Test
    void signIn() {
        when(accountCRUDPort.findByLogin(login1)).thenReturn(account1);
        UserCredentials userCredentials = new UserCredentials(login1, password1);

        UserDetails userDetails = accountService.signIn(userCredentials);

        assertEquals(new UserPrincipal(account1).getUsername(), userDetails.getUsername());
        assertEquals(new UserPrincipal(account1).getPassword(), userDetails.getPassword());
    }
}