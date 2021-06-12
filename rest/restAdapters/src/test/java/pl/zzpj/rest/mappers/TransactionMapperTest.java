package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;
import pl.zzpj.dto.AccessLevelDto;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.dto.CurrencyDto;
import pl.zzpj.dto.TransactionDto;

import java.math.BigDecimal;
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
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setDate(timestamp);
        transaction.setFrom(from);
        transaction.setFromCurrency(from.getCurrency());
        transaction.setTo(to);
        transaction.setToCurrency(to.getCurrency());

        TransactionDto transactionDto = TransactionMapper.mapToTransactionDto(transaction);

        assertEquals(BigDecimal.valueOf(100), transactionDto.getAmount());
        assertEquals(timestamp, transactionDto.getDate());
        assertEquals(from.getLogin(), transactionDto.getFrom().getLogin());
        assertEquals(from.getCurrency(), CurrencyMapper.mapToCurrency(transactionDto.getFromCurrency()));
        assertEquals(to.getLogin(), transactionDto.getTo().getLogin());
        assertEquals(to.getCurrency(), CurrencyMapper.mapToCurrency(transactionDto.getToCurrency()));
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
        transactionDto.setAmount(BigDecimal.valueOf(100));
        transactionDto.setDate(timestamp);
        transactionDto.setFrom(from);
        transactionDto.setFromCurrency(from.getCurrency());
        transactionDto.setTo(to);
        transactionDto.setToCurrency(to.getCurrency());

        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);

        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        assertEquals(timestamp, transaction.getDate());
        assertEquals(from.getLogin(), transaction.getFrom().getLogin());
        assertEquals(from.getCurrency(), CurrencyMapper.mapToCurrencyDto(transaction.getFromCurrency()));
        assertEquals(to.getLogin(), transaction.getTo().getLogin());
        assertEquals(to.getCurrency(), CurrencyMapper.mapToCurrencyDto(transaction.getToCurrency()));
    }
}
