package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repositories.TransactionRepository;
import pl.zzpj.repository.mappers.AccountMapper;
import pl.zzpj.repository.mappers.TransactionMapper;

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
    public void addTransaction(Transaction transaction) {
        this.transactionRepository.save(TransactionMapper.mapToTransactionEnt(transaction));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream().map(TransactionMapper::mapToTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAllByAccount(Account account) {
        AccountEnt accountEnt = AccountMapper.mapToAccountEnt(account);
        return transactionRepository.findAllByFromIdOrToId(accountEnt, accountEnt).stream().map(TransactionMapper::mapToTransaction)
                .collect(Collectors.toList());
    }
}
