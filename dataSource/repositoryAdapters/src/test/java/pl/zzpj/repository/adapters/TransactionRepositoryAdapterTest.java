package pl.zzpj.repository.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repository.mappers.AccountMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class TransactionRepositoryAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    TransactionRepositoryAdapter transactionRepositoryAdapter;

    @Spy
    Account from;
    @Spy
    Account to;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        from = new Account();
        from.setAccountState(BigDecimal.valueOf(9999D));
        from.setLogin("konto1");
        from.setCurrency(Currency.GBP);
        from.setAccessLevel(new AccessLevel());
        to = new Account();
        to.setAccountState(BigDecimal.valueOf(111D));
        to.setLogin("konto2");
        to.setCurrency(Currency.PLN);
        to.setAccessLevel(new AccessLevel());

        when(accountRepository.findByLogin(from.getLogin())).thenReturn(AccountMapper.mapToAccountEnt(from));
        when(accountRepository.findByLogin(to.getLogin())).thenReturn(AccountMapper.mapToAccountEnt(to));
    }

    @Test
    void withdrawTest() {
        doAnswer(invocationOnMock -> {
            from.setAccountState(from.getAccountState().subtract(BigDecimal.valueOf(2000)));
            return null;
        }).when(accountRepository).save(any());
        transactionRepositoryAdapter.withdraw(from, BigDecimal.valueOf(2000));

        assertEquals(7999, from.getAccountState());
    }

    @Test
    void withdrawExceptionTest() {
        assertThrows(IllegalStateException.class, () ->transactionRepositoryAdapter.withdraw(from, BigDecimal.valueOf(10_000D)));
    }

    @Test
    void depositTest() {
        doAnswer(invocationOnMock -> {
            from.setAccountState(from.getAccountState().add(BigDecimal.valueOf(2000)));
            return null;
        }).when(accountRepository).save(any());
        transactionRepositoryAdapter.deposit(from, BigDecimal.valueOf(2000));

        assertEquals(11_999, from.getAccountState());
    }

    @Test
    void transferTest() {
        doAnswer(invocationOnMock -> {
            from.setAccountState(from.getAccountState().subtract(BigDecimal.valueOf(2000 * 1.5)));
            to.setAccountState(to.getAccountState().add(BigDecimal.valueOf(2000 * 1.5)));
            return null;
        }).when(accountRepository).saveAll(any());
        transactionRepositoryAdapter.transfer(from, to, BigDecimal.valueOf(2000), BigDecimal.valueOf(1.5));

        assertEquals(6999, from.getAccountState());
        assertEquals(3111, to.getAccountState());
    }
}
