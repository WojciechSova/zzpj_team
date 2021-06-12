package pl.zzpj.repository.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.entities.CurrencyEnt;
import pl.zzpj.entities.TransactionEnt;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    @Test
    void mapToTransactionEnt() {
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setLevel("CLIENT");
        Account from = new Account();
        from.setLogin("from");
        from.setCurrency(Currency.EUR);
        from.setAccessLevel(accessLevel);
        Account to = new Account();
        to.setLogin("to");
        to.setCurrency(Currency.EUR);
        to.setAccessLevel(accessLevel);
        Timestamp timestamp = Timestamp.from(Instant.now());

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setDate(timestamp);
        transaction.setFrom(from);
        transaction.setTo(to);

        TransactionEnt transactionEnt = TransactionMapper.mapToTransactionEnt(transaction);

        assertEquals(BigDecimal.valueOf(100), transactionEnt.getAmount());
        assertEquals(timestamp, transactionEnt.getDate());
        assertEquals(from.getLogin(), transactionEnt.getFromId().getLogin());
        assertEquals(to.getLogin(), transactionEnt.getToId().getLogin());
    }

    @Test
    void mapToTransaction() {
        AccessLevelEnt accessLevel = new AccessLevelEnt();
        accessLevel.setLevel("CLIENT");
        AccountEnt from = new AccountEnt();
        from.setLogin("from");
        from.setCurrency(CurrencyEnt.EUR);
        from.setAccessLevel(accessLevel);
        AccountEnt to = new AccountEnt();
        to.setLogin("to");
        to.setCurrency(CurrencyEnt.EUR);
        to.setAccessLevel(accessLevel);
        Timestamp timestamp = Timestamp.from(Instant.now());

        TransactionEnt transactionEnt = new TransactionEnt();
        transactionEnt.setAmount(BigDecimal.valueOf(100));
        transactionEnt.setDate(timestamp);
        transactionEnt.setFromId(from);
        transactionEnt.setToId(to);

        Transaction transaction = TransactionMapper.mapToTransaction(transactionEnt);

        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        assertEquals(timestamp, transaction.getDate());
        assertEquals(from.getLogin(), transaction.getFrom().getLogin());
        assertEquals(to.getLogin(), transaction.getTo().getLogin());
    }
}
