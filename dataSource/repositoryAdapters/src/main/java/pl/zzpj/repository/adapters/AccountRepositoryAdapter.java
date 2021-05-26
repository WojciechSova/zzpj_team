package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.Account;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repository.mappers.AccountMapper;
import pl.zzpj.repository.mappers.CurrencyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryAdapter implements AccountCRUDPort {

    final AccountRepository accountRepository;

    @Autowired
    public AccountRepositoryAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.saveAndFlush(AccountMapper.mapToAccountEnt(account));
    }

    @Override
    public void removeAccount(Account account) {

    }

    @Override
    public void updateAccount(String login, Account account) {
        AccountEnt acc = accountRepository.findByLogin(login);
        acc.setAccountState(account.getAccountState());
        acc.setCurrency(CurrencyMapper.mapToCurrencyEnt(account.getCurrency()));
        acc.setFirstName(account.getFirstName());
        acc.setLastName(account.getLastName());
        acc.setDebt(account.getDebt());
        accountRepository.save(acc);
    }

    @Override
    public Account findByLogin(String login) {
        return AccountMapper.mapToAccount(accountRepository.findByLogin(login));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream().map(AccountMapper::mapToAccount).collect(Collectors.toList());
    }
}
