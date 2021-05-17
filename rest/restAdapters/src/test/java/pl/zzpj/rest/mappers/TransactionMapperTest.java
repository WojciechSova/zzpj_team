package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;
import pl.zzpj.modelDto.AccessLevelDto;
import pl.zzpj.modelDto.AccountDto;
import pl.zzpj.modelDto.CurrencyDto;
import pl.zzpj.modelDto.TransactionDto;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    @Test
    void mapToTransactionDto() {
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
        transaction.setAmount(100);
        transaction.setDate(timestamp);
        transaction.setFrom(from);
        transaction.setTo(to);

        TransactionDto transactionDto = TransactionMapper.mapToTransactionDto(transaction);

        assertEquals(100, transactionDto.getAmount());
        assertEquals(timestamp, transactionDto.getDate());
        assertEquals(from.getLogin(), transactionDto.getFrom().getLogin());
        assertEquals(to.getLogin(), transactionDto.getTo().getLogin());
    }

    @Test
    void mapToTransaction() {
        AccessLevelDto accessLevel = new AccessLevelDto();
        accessLevel.setLevel("CLIENT");
        AccountDto from = new AccountDto();
        from.setLogin("from");
        from.setCurrency(CurrencyDto.EUR);
        from.setAccessLevel(accessLevel);
        AccountDto to = new AccountDto();
        to.setLogin("to");
        to.setCurrency(CurrencyDto.EUR);
        to.setAccessLevel(accessLevel);
        Timestamp timestamp = Timestamp.from(Instant.now());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100);
        transactionDto.setDate(timestamp);
        transactionDto.setFrom(from);
        transactionDto.setTo(to);

        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);

        assertEquals(100, transaction.getAmount());
        assertEquals(timestamp, transaction.getDate());
        assertEquals(from.getLogin(), transaction.getFrom().getLogin());
        assertEquals(to.getLogin(), transaction.getTo().getLogin());
    }
}
