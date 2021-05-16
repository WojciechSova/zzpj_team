package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.Currency;
import pl.zzpj.model.Transaction;
import pl.zzpj.model.users.Account;
import pl.zzpj.model.users.Client;
import pl.zzpj.modelDto.CurrencyDto;
import pl.zzpj.modelDto.TransactionDto;
import pl.zzpj.modelDto.usersDto.ClientDto;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    @Test
    void mapToTransactionDto() {
        Client from = new Client();
        from.setLogin("from");
        from.setCurrency(Currency.EUR);
        Client to = new Client();
        to.setLogin("to");
        to.setCurrency(Currency.EUR);
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
        ClientDto from = new ClientDto();
        from.setLogin("from");
        from.setCurrency(CurrencyDto.EUR);
        ClientDto to = new ClientDto();
        to.setLogin("to");
        to.setCurrency(CurrencyDto.EUR);
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
