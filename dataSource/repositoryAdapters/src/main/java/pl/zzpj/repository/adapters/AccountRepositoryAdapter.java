package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.Account;
import pl.zzpj.repositories.AccessLevelRepository;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repository.mappers.AccountMapper;
import pl.zzpj.repository.mappers.CurrencyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryAdapter implements AccountCRUDPort {

    private final AccountRepository accountRepository;
    private final AccessLevelRepository accessLevelRepository;

    @Autowired
    public AccountRepositoryAdapter(AccountRepository accountRepository, AccessLevelRepository accessLevelRepository) {
        this.accountRepository = accountRepository;
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public void addAccount(Account account) {
        AccessLevelEnt accessLevelEnt = accessLevelRepository.findByLevel(account.getAccessLevel().getLevel());
        AccountEnt accountEnt = AccountMapper.mapToAccountEnt(account);
        accountEnt.setAccessLevel(accessLevelEnt);
        accountRepository.saveAndFlush(accountEnt);
    }

    @Override
    public void removeAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(AccountMapper.mapToAccountEnt(account));
    }

    @Override
    public Account findByLogin(String login) {
        return AccountMapper.mapToAccount(accountRepository.findByLogin(login));
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return AccountMapper.mapToAccount(accountRepository.findByAccountNumber(accountNumber));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream().map(AccountMapper::mapToAccount).collect(Collectors.toList());
    }
}
