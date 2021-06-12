package pl.zzpj.controller;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionUseCase {

    void withdraw(Account account, BigDecimal amount);

    void deposit(Account account, BigDecimal amount);

    void transfer(Account from, Account to, BigDecimal amount, BigDecimal rate) throws Exception;

    List<Transaction> findAll();
}
