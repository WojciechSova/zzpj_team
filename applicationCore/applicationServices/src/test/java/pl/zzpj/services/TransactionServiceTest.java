package pl.zzpj.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.exceptions.RequestFailedException;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.loans.LoanCalculator;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private LoanCalculator loanCalculator;

    @Spy
    private AccountCRUDPort accountCRUDPort;

    @Spy
    private TransactionPort transactionPort;

    @Spy
    Account from;

    @Spy
    Account to;

    Account account = new Account();

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
        when(accountCRUDPort.findByAccountNumber(to.getLogin())).thenReturn(to);

        account.setCurrency(Currency.EUR);
        account.setPassword("pw");
        account.setFirstName("fn");
        account.setLastName("ln");
        account.setAccessLevel(null);
        account.setAccountState(BigDecimal.ZERO);
        account.setDebt(BigDecimal.ZERO);
    }

    @Test
    void withdrawTest() {
        transactionService.withdraw("konto1", BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(7999.), from.getAccountState());
        verify(transactionPort).addTransaction(transactionCaptor.capture());

        Transaction transaction = transactionCaptor.getValue();
        assertEquals(transaction.getFrom(), from);
        assertEquals(transaction.getFromCurrency(), from.getCurrency());
        assertEquals(transaction.getTo(), null);
        assertEquals(transaction.getToCurrency(), from.getCurrency());
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(-2000));
        assertEquals(transaction.getRate(), BigDecimal.valueOf(1));
    }

    @Test
    void withdrawExceptionTest() {
        assertThrows(IllegalStateException.class, () -> transactionService.withdraw("konto1", BigDecimal.valueOf(10_000D)));
    }

    @Test
    void depositTest() {
        transactionService.deposit("konto1", BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(11_999.), from.getAccountState());
        verify(accountCRUDPort).updateAccount(from);
        verify(transactionPort).addTransaction(transactionCaptor.capture());

        Transaction transaction = transactionCaptor.getValue();
        assertEquals(transaction.getFrom(), null);
        assertEquals(transaction.getFromCurrency(), from.getCurrency());
        assertEquals(transaction.getTo(), from);
        assertEquals(transaction.getToCurrency(), from.getCurrency());
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(2000));
        assertEquals(transaction.getRate(), BigDecimal.valueOf(1));
    }

    @Test
    void transferTest() throws RequestFailedException, UnirestException {
        when(currencyExchangeService.exchangeFromTo(any(), any())).thenReturn(new BigDecimal("1.5"));
        transactionService.transfer("konto1", "konto2", BigDecimal.valueOf(2000));

        assertEquals(BigDecimal.valueOf(7999.), from.getAccountState());
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

    @Test
    void takeLoan() {
        when(accountCRUDPort.findByLogin("fn")).thenReturn(account);
        when(loanCalculator.calculateMaxLoanAmount(account)).thenReturn(BigDecimal.valueOf(2000L));

        Timestamp before = Timestamp.from(Instant.now().minusMillis(1));
        try {
            transactionService.takeLoan("fn", BigDecimal.ONE);
        } catch (LoanNotAvailableException e) {
            e.printStackTrace();
        }
        Timestamp after = Timestamp.from(Instant.now().plusMillis(1));

        assertEquals(BigDecimal.ONE, account.getAccountState());
        assertEquals(BigDecimal.valueOf(1.1), account.getDebt());

        verify(transactionPort).addTransaction(transactionCaptor.capture());
        Transaction transaction = transactionCaptor.getValue();
        assertEquals(BigDecimal.ONE, transaction.getAmount());
        assertTrue(transaction.getIsLoan());
        assertEquals(account.getCurrency(), transaction.getFromCurrency());
        assertEquals(account.getCurrency(), transaction.getToCurrency());
        assertEquals(account, transaction.getTo());
        assertNull(transaction.getFrom());
        assertEquals(BigDecimal.ONE, transaction.getRate());
        assertTrue(transaction.getDate().after(before) && transaction.getDate().before(after));
    }
}
