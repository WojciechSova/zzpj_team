package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TransactionRepositoryAdapter implements TransactionPort {

    AccountRepository accountRepository;

    @Autowired
    public TransactionRepositoryAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdraw(Account account, double amount) {
        AccountEnt acc = accountRepository.findByLogin(account.getLogin());
        Double accountState = acc.getAccountState();
        if (accountState - amount >= 0) {
            acc.setAccountState(accountState - amount);
            accountRepository.save(acc);
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    @Override
    public void deposit(Account account, double amount) {
        AccountEnt acc = accountRepository.findByLogin(account.getLogin());
        Double accountState = acc.getAccountState();
        acc.setAccountState(accountState + amount);
        accountRepository.save(acc);
    }

    @Override
    public void transfer(Account from, Account to, double amount, BigDecimal rate) {
        AccountEnt accFrom = accountRepository.findByLogin(from.getLogin());
        AccountEnt accTo = accountRepository.findByLogin(to.getLogin());
        Double accountState = accFrom.getAccountState();
        if (accountState - amount >= 0) {
            accFrom.setAccountState(accountState - amount * rate.doubleValue());
            accTo.setAccountState(accountState + amount * rate.doubleValue());
            accountRepository.saveAll(List.of(accFrom, accTo));
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }
}
