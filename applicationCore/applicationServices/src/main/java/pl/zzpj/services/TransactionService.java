package pl.zzpj.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.TransactionUseCase;
import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.exceptions.RequestFailedException;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionService implements TransactionUseCase {

    private final AccountCRUDPort accountCRUDPort;

    private final BigDecimal interest = BigDecimal.valueOf(1.1);

    private final TransactionPort transactionPort;

    @Autowired
    public TransactionService(AccountCRUDPort accountCRUDPort, TransactionPort transactionPort) {
        this.accountCRUDPort = accountCRUDPort;
        this.transactionPort = transactionPort;
    }

    @Override
    public void withdraw(String login, BigDecimal amount) {
        Account acc = accountCRUDPort.findByLogin(login);
        BigDecimal accountState = acc.getAccountState();
        if (accountState.subtract(amount).doubleValue() >= 0) {
            acc.setAccountState(accountState.subtract(amount));
            accountCRUDPort.updateAccount(acc);

            Transaction transaction = new Transaction();
            transaction.setFrom(acc);
            transaction.setFromCurrency(acc.getCurrency());
            transaction.setTo(null);
            transaction.setToCurrency(acc.getCurrency());
            transaction.setAmount(amount.multiply(new BigDecimal(-1)));
            transaction.setRate(new BigDecimal(1));
            transaction.setDate(Timestamp.from(Instant.now()));
            transaction.setIsLoan(false);
            transactionPort.addTransaction(transaction);
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    @Override
    public void deposit(String login, BigDecimal amount) {
        Account acc = accountCRUDPort.findByLogin(login);
        BigDecimal accountState = acc.getAccountState();
        acc.setAccountState(accountState.add(amount));
        accountCRUDPort.updateAccount(acc);

        Transaction transaction = new Transaction();
        transaction.setFrom(null);
        transaction.setFromCurrency(acc.getCurrency());
        transaction.setTo(acc);
        transaction.setToCurrency(acc.getCurrency());
        transaction.setAmount(amount);
        transaction.setRate(new BigDecimal(1));
        transaction.setDate(Timestamp.from(Instant.now()));
        transaction.setIsLoan(false);
        transactionPort.addTransaction(transaction);
    }

    @Override
    public void transfer(String loginFrom, String accountNumberTo, BigDecimal amount) throws RequestFailedException, UnirestException {
        Account accFrom = accountCRUDPort.findByLogin(loginFrom);
        Account accTo = accountCRUDPort.findByAccountNumber(accountNumberTo);
        BigDecimal accountState = accFrom.getAccountState();
        BigDecimal rate = CurrencyExchangeService.exchangeFromTo(accFrom.getCurrency(), accTo.getCurrency());
        if (accountState.subtract(amount).doubleValue() >= 0) {
            BigDecimal convertedAmount = amount.multiply(BigDecimal.valueOf(rate.doubleValue()));
            accFrom.setAccountState(accFrom.getAccountState().subtract(amount));
            accTo.setAccountState(accTo.getAccountState().add(convertedAmount));
            accountCRUDPort.updateAccount(accFrom);
            accountCRUDPort.updateAccount(accTo);

            Transaction transaction = new Transaction();
            transaction.setFrom(accFrom);
            transaction.setFromCurrency(accFrom.getCurrency());
            transaction.setTo(accTo);
            transaction.setToCurrency(accTo.getCurrency());
            transaction.setAmount(amount);
            transaction.setRate(rate);
            transaction.setDate(Timestamp.from(Instant.now()));
            transaction.setIsLoan(false);
            transactionPort.addTransaction(transaction);
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    @Override
    public void takeLoan(String login, BigDecimal amount) throws LoanNotAvailableException {
        Account account = accountCRUDPort.findByLogin(login);

        if (amount.compareTo(getMaxLoanAmount(account)) > 0) {
            throw new LoanNotAvailableException("Loan not available");
        }

        Transaction transaction = new Transaction();
        transaction.setTo(account);
        transaction.setToCurrency(account.getCurrency());
        transaction.setFromCurrency(account.getCurrency());
        transaction.setAmount(amount);
        transaction.setRate(BigDecimal.ONE);
        transaction.setDate(Timestamp.from(Instant.now()));
        transaction.setIsLoan(true);

        account.setDebt(account.getDebt().add(amount.multiply(interest)));
        account.setAccountState(account.getAccountState().add(amount));
        accountCRUDPort.updateAccount(account);
        transactionPort.addTransaction(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionPort.findAll();
    }

    private BigDecimal getMaxLoanAmount(Account account) {
        return BigDecimal.valueOf(2000L);
    }
}
