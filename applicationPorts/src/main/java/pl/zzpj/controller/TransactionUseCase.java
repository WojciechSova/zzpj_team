package pl.zzpj.controller;

import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionUseCase {

    void withdraw(String login, BigDecimal amount);

    void deposit(String login, BigDecimal amount);

    void transfer(String loginFrom, String accountNumberTo, BigDecimal amount) throws Exception;

    void takeLoan(String login, BigDecimal amount) throws LoanNotAvailableException;

    void payBackLoan(String login, BigDecimal amount);

    List<Transaction> findAll();
}
