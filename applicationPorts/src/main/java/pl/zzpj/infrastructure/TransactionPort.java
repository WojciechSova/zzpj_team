package pl.zzpj.infrastructure;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionPort {

    void withdraw(Account account, BigDecimal amount);

    void deposit(Account account, BigDecimal amount);

    void transfer(Account from, Account to, BigDecimal amount, BigDecimal rate);

    List<Transaction> findAll();
}
