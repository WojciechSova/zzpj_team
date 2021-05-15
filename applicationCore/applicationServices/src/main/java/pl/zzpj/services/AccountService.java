package pl.zzpj.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.users.Account;

import java.util.List;

@Service
public class AccountService implements AccountCRUDUseCase {

    private final AccountCRUDPort accountCRUDPort;

    @Autowired
    public AccountService(AccountCRUDPort accountCRUDPort) {
        this.accountCRUDPort = accountCRUDPort;
    }

    @Override
    public void addAccount(Account account) {
        accountCRUDPort.addAccount(account);
    }

    @Override
    public void removeAccount(Account account) {
        accountCRUDPort.removeAccount(account);
    }

    @Override
    public void updateAccount(String login, Account account) {
        accountCRUDPort.updateAccount(login, account);
    }

    @Override
    public Account findByLogin(String login) {
        return accountCRUDPort.findByLogin(login);
    }

    @Override
    public List<Account> findAll() {
        return accountCRUDPort.findAll();
    }
}
