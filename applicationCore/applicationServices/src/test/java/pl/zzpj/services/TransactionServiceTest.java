package pl.zzpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Spy
    private AccountCRUDPort accountCRUDPort;

    @Spy
    private TransactionPort transactionPort;

    @Spy
    Account from;

    @Spy
    Account to;

    @Captor
    private ArgumentCaptor<Transaction> transactionCaptor;

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

        when(accountCRUDPort.findByLogin(from.getLogin())).thenReturn(from);
        when(accountCRUDPort.findByLogin(to.getLogin())).thenReturn(to);
    }

    @Test
    void withdrawTest() {
        transactionService.withdraw(from, BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(7999.), from.getAccountState());
        verify(transactionPort).addTransaction(transactionCaptor.capture());

        Transaction transaction = transactionCaptor.getValue();
        assertEquals(transaction.getFrom(), from);
        assertEquals(transaction.getFromCurrency(), from.getCurrency());
        assertEquals(transaction.getTo(), from);
        assertEquals(transaction.getToCurrency(), from.getCurrency());
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(-2000));
        assertEquals(transaction.getRate(), BigDecimal.valueOf(1));
    }

    @Test
    void withdrawExceptionTest() {
        assertThrows(IllegalStateException.class, () -> transactionService.withdraw(from, BigDecimal.valueOf(10_000D)));
    }

    @Test
    void depositTest() {
        transactionService.deposit(from, BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(11_999.), from.getAccountState());
        verify(accountCRUDPort).updateAccount(from);
        verify(transactionPort).addTransaction(transactionCaptor.capture());

        Transaction transaction = transactionCaptor.getValue();
        assertEquals(transaction.getFrom(), from);
        assertEquals(transaction.getFromCurrency(), from.getCurrency());
        assertEquals(transaction.getTo(), from);
        assertEquals(transaction.getToCurrency(), from.getCurrency());
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(2000));
        assertEquals(transaction.getRate(), BigDecimal.valueOf(1));
    }

    @Test
    void transferTest() {
        transactionService.transfer(from, to, BigDecimal.valueOf(2000), BigDecimal.valueOf(1.5));

        assertEquals(BigDecimal.valueOf(6999.), from.getAccountState());
        assertEquals(BigDecimal.valueOf(3111.), to.getAccountState());

        verify(accountCRUDPort).updateAccount(from);
        verify(accountCRUDPort).updateAccount(to);
        verify(transactionPort).addTransaction(transactionCaptor.capture());

        Transaction transaction = transactionCaptor.getValue();
        assertEquals(transaction.getFrom(), from);
        assertEquals(transaction.getFromCurrency(), from.getCurrency());
        assertEquals(transaction.getTo(), to);
        assertEquals(transaction.getToCurrency(), to.getCurrency());
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(2000));
        assertEquals(transaction.getRate(), BigDecimal.valueOf(1.5));
    }
}
