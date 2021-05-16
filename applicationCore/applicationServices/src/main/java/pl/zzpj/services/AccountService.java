package pl.zzpj.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.controller.SignInUseCase;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.UserCredentials;
import pl.zzpj.model.UserPrincipal;
import pl.zzpj.model.Account;

import java.util.List;

@Service
public class AccountService implements AccountCRUDUseCase, UserDetailsService, SignInUseCase {

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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountCRUDPort.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserPrincipal(account);
    }

    @Override
    public UserDetails signIn(UserCredentials userCredentials) {
        return loadUserByUsername(userCredentials.getLogin());
    }
}
