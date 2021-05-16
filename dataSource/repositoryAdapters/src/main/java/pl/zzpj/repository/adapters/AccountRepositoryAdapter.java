package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.Account;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repository.mappers.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryAdapter implements AccountCRUDPort {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void addAccount(Account account) {
        accountRepository.saveAndFlush(AccountMapper.mapToAccountEnt(account));
    }

    @Override
    public void removeAccount(Account account) {

    }

    @Override
    public void updateAccount(String login, Account account) {

    }

    @Override
    public Account findByLogin(String login) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream().map(AccountMapper::mapToAccount).collect(Collectors.toList());
    }
}
