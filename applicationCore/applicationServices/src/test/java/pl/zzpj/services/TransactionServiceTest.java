package pl.zzpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private TransactionPort transactionPort;

    @Mock
    private AccountCRUDPort accountCRUDPort;

    @InjectMocks
    private TransactionService transactionService;

    Account account = new Account();

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);

        account.setCurrency(Currency.EUR);
        account.setPassword("pw");
        account.setFirstName("fn");
        account.setLastName("ln");
        account.setAccessLevel(null);
        account.setAccountState(BigDecimal.ZERO);
        account.setDebt(BigDecimal.ZERO);
    }

    @Test
    void takeLoan() {
        when(accountCRUDPort.findByLogin("fn")).thenReturn(account);

        try {
            transactionService.takeLoan("fn", BigDecimal.ONE);
        } catch (LoanNotAvailableException e) {
            e.printStackTrace();
        }

        assertEquals(BigDecimal.ONE, account.getAccountState());
        assertEquals(BigDecimal.valueOf(1.1), account.getDebt());
        // Tutaj sprawdzanie transakcji czy się dodała
    }
}
