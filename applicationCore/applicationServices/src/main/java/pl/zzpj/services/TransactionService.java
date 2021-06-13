package pl.zzpj.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final BigDecimal interest = BigDecimal.valueOf(1.1);

    private final TransactionPort transactionPort;
    private final AccountCRUDPort accountCRUDPort;

    @Autowired
    public TransactionService(TransactionPort transactionPort, AccountCRUDPort accountCRUDPort) {
        this.transactionPort = transactionPort;
        this.accountCRUDPort = accountCRUDPort;
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) {
        transactionPort.withdraw(account, amount);
    }

    @Override
    public void deposit(Account account, BigDecimal amount) {
        transactionPort.deposit(account, amount);
    }

    @Override
    public void transfer(Account from, Account to, BigDecimal amount, BigDecimal rate) throws Exception {
        transactionPort.transfer(from, to, amount, CurrencyExchangeService.exchangeFromTo(from.getCurrency(), to.getCurrency()));
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
