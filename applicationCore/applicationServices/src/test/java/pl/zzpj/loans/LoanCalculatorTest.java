package pl.zzpj.loans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;

class LoanCalculatorTest {

    @Mock
    private TransactionPort transactionPort;

    @InjectMocks
    private LoanCalculator loanCalculator;

    private Account account;
    private List<Transaction> transactions;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);

        account = new Account();
        account.setCurrency(Currency.EUR);

        Transaction transaction1 = new Transaction();
        transaction1.setTo(account);
        transaction1.setToCurrency(account.getCurrency());
        transaction1.setFromCurrency(account.getCurrency());
        transaction1.setAmount(BigDecimal.valueOf(10000L));
        transaction1.setRate(BigDecimal.ONE);
        transaction1.setDate(Timestamp.from(Instant.now()));
        transaction1.setIsLoan(true);

        Transaction transaction2 = new Transaction();
        transaction2.setFrom(account);
        transaction2.setToCurrency(account.getCurrency());
        transaction2.setFromCurrency(account.getCurrency());
        transaction2.setAmount(BigDecimal.valueOf(2700L));
        transaction2.setRate(BigDecimal.ONE);
        transaction2.setDate(Timestamp.from(Instant.now()));
        transaction2.setIsLoan(true);

        Transaction transaction3 = new Transaction();
        transaction3.setTo(account);
        transaction3.setToCurrency(account.getCurrency());
        transaction3.setFromCurrency(account.getCurrency());
        transaction3.setAmount(BigDecimal.valueOf(3000L));
        transaction3.setRate(BigDecimal.ONE);
        transaction3.setDate(Timestamp.from(Instant.now()));
        transaction3.setIsLoan(false);

        transactions = List.of(
                transaction1,
                transaction2,
                transaction3
        );
    }

    @Test
    void calculateMaxLoanAmount() {
        when(transactionPort.findAllByAccount(account)).thenReturn(transactions);

        BigDecimal maxLoan = loanCalculator.calculateMaxLoanAmount(account);

        System.out.println(maxLoan);
    }
}
