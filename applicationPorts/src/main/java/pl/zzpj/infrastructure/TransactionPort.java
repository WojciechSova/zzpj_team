package pl.zzpj.infrastructure;

import pl.zzpj.model.Account;

import java.math.BigDecimal;

public interface TransactionPort {

    void withdraw(Account account, BigDecimal amount);

    void deposit(Account account, BigDecimal amount);

    void transfer(Account from, Account to, BigDecimal amount, BigDecimal rate);
}
