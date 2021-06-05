package pl.zzpj.controller;

import pl.zzpj.model.Account;

import java.math.BigDecimal;

public interface TransactionUseCase {

    void withdraw(Account account, double amount);

    void deposit(Account account, double amount);

    void transfer(Account from, Account to, double amount, BigDecimal rate) throws Exception;
}
