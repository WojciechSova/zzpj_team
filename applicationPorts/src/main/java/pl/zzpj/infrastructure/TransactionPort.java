package pl.zzpj.infrastructure;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.util.List;

public interface TransactionPort {

    void addTransaction(Transaction transaction);

    List<Transaction> findAll();

    List<Transaction> findAllByAccount(Account account);
}
