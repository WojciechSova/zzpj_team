package pl.zzpj.loans;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrustedStrategyTest {

    @Test
    void calculate() {
        Account account = new Account();
        account.setDebt(BigDecimal.ZERO);
        Account account2 = new Account();
        account2.setDebt(BigDecimal.ZERO);

        Transaction transaction1 = new Transaction();
        transaction1.setTo(account);
        transaction1.setFrom(account2);
        transaction1.setToCurrency(account.getCurrency());
        transaction1.setFromCurrency(account.getCurrency());
        transaction1.setAmount(BigDecimal.valueOf(3000L));
        transaction1.setRate(BigDecimal.ONE);
        transaction1.setDate(Timestamp.from(Instant.now()));
        transaction1.setIsLoan(false);


        BigDecimal bd = new TrustedStrategy().calculate(
                List.of(transaction1),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                account
        );

        assertEquals(BigDecimal.valueOf(900000, 2), bd);
    }
}
