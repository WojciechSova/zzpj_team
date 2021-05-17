package pl.zzpj.repository.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.model.Account;
import pl.zzpj.repositories.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountRepositoryAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountRepositoryAdapter accountRepositoryAdapter;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByLogin() {
        AccountEnt accountEnt = new AccountEnt();
        AccessLevelEnt accessLevelEnt = new AccessLevelEnt();
        accessLevelEnt.setLevel("al");
        accountEnt.setAccessLevel(accessLevelEnt);
        accountEnt.setLogin("login");
        when(accountRepository.findByLogin("login")).thenReturn(accountEnt);

        Account account = accountRepositoryAdapter.findByLogin("login");

        assertEquals(accountEnt.getLogin(), account.getLogin());
    }
}
