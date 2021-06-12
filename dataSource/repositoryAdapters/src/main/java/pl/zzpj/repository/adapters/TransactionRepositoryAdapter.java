package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.entities.TransactionEnt;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repositories.TransactionRepository;
import pl.zzpj.repository.mappers.TransactionMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryAdapter implements TransactionPort {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionRepositoryAdapter(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) {
        AccountEnt acc = accountRepository.findByLogin(account.getLogin());
        BigDecimal accountState = acc.getAccountState();
        if (accountState.subtract(amount).doubleValue() >= 0) {
            acc.setAccountState(accountState.subtract(amount));
            accountRepository.save(acc);

            TransactionEnt transactionEnt = new TransactionEnt();
            transactionEnt.setFromId(acc);
            transactionEnt.setFromCurrency(acc.getCurrency());
            transactionEnt.setToId(acc);
            transactionEnt.setToCurrency(acc.getCurrency());
            transactionEnt.setAmount(amount.multiply(new BigDecimal(-1)));
            transactionEnt.setRate(new BigDecimal(1));
            transactionEnt.setDate(Timestamp.from(Instant.now()));
            transactionRepository.save(transactionEnt);
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    @Override
    public void deposit(Account account, BigDecimal amount) {
        AccountEnt acc = accountRepository.findByLogin(account.getLogin());
        BigDecimal accountState = acc.getAccountState();
        acc.setAccountState(accountState.add(amount));
        accountRepository.save(acc);

        TransactionEnt transactionEnt = new TransactionEnt();
        transactionEnt.setFromId(acc);
        transactionEnt.setFromCurrency(acc.getCurrency());
        transactionEnt.setToId(acc);
        transactionEnt.setToCurrency(acc.getCurrency());
        transactionEnt.setAmount(amount);
        transactionEnt.setRate(new BigDecimal(1));
        transactionEnt.setDate(Timestamp.from(Instant.now()));
        transactionRepository.save(transactionEnt);
    }

    @Override
    public void transfer(Account from, Account to, BigDecimal amount, BigDecimal rate) {
        AccountEnt accFrom = accountRepository.findByLogin(from.getLogin());
        AccountEnt accTo = accountRepository.findByLogin(to.getLogin());
        BigDecimal accountState = accFrom.getAccountState();
        if (accountState.subtract(amount).doubleValue() >= 0) {
            BigDecimal convertedAmount = amount.multiply(BigDecimal.valueOf(rate.doubleValue()));
            accFrom.setAccountState(accountState.subtract(convertedAmount));
            accTo.setAccountState(accountState.add(convertedAmount));
            accountRepository.saveAll(List.of(accFrom, accTo));

            TransactionEnt transactionEnt = new TransactionEnt();
            transactionEnt.setFromId(accFrom);
            transactionEnt.setFromCurrency(accFrom.getCurrency());
            transactionEnt.setToId(accTo);
            transactionEnt.setToCurrency(accTo.getCurrency());
            transactionEnt.setAmount(amount);
            transactionEnt.setRate(rate);
            transactionEnt.setDate(Timestamp.from(Instant.now()));
            transactionRepository.save(transactionEnt);
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream().map(TransactionMapper::mapToTransaction)
                .collect(Collectors.toList());
    }
}
