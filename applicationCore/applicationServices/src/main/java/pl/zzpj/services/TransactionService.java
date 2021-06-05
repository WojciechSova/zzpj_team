package pl.zzpj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.TransactionUseCase;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;

import java.math.BigDecimal;

@Service
public class TransactionService implements TransactionUseCase {

    private final TransactionPort transactionPort;

    @Autowired
    public TransactionService(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    @Override
    public void withdraw(Account account, double amount) {
        transactionPort.withdraw(account, amount);
    }

    @Override
    public void deposit(Account account, double amount) {
        transactionPort.deposit(account, amount);
    }

    @Override
    public void transfer(Account from, Account to, double amount, BigDecimal rate) throws Exception {
        transactionPort.transfer(from, to, amount, CurrencyExchangeService.exchangeFromTo(from.getCurrency(), to.getCurrency()));
    }
}
