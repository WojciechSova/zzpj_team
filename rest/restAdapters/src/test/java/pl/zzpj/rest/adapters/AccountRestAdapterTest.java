package pl.zzpj.rest.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountRestAdapterTest {

    @Mock
    private AccountCRUDUseCase accountCRUDUseCase;

    @InjectMocks
    private AccountRestAdapter accountRestAdapter;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByLogin() {
        Account account = new Account();
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setLevel("al");
        account.setAccessLevel(accessLevel);
        account.setLogin("login");
        when(accountCRUDUseCase.findByLogin("login")).thenReturn(account);

        AccountDto accountDto = accountRestAdapter.findByLogin("login");

        assertEquals(account.getLogin(), accountDto.getLogin());
    }

    @Test
    void blockAccount() {
        accountRestAdapter.blockAccount("login0");
        verify(accountCRUDUseCase).blockAccount("login0");
    }

    @Test
    void unblockAccount() {
        accountRestAdapter.unblockAccount("login0");
        verify(accountCRUDUseCase).unblockAccount("login0");
    }
}
