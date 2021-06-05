package pl.zzpj.infrastructure;

import pl.zzpj.model.Account;

import java.math.BigDecimal;

public interface TransactionPort {

    void withdraw(Account account, double amount);

    void deposit(Account account, double amount);

    void transfer(Account from, Account to, double amount, BigDecimal rate);
}
