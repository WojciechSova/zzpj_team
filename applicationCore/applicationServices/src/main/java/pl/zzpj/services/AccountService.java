package pl.zzpj.services;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.controller.SignInUseCase;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.UserPrincipal;
import pl.zzpj.model.Account;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements AccountCRUDUseCase, SignInUseCase {

    private final AccountCRUDPort accountCRUDPort;

    @Autowired
    public AccountService(AccountCRUDPort accountCRUDPort) {
        this.accountCRUDPort = accountCRUDPort;
    }

    @Override
    public void addAccount(Account account) {
        account.setAccountState(BigDecimal.ZERO);
        account.setAccountNumber(generateAccountNumber());
        account.setDebt(BigDecimal.ZERO);
        accountCRUDPort.addAccount(account);
    }

    private String generateAccountNumber() {
        return RandomStringUtils.random(16, false, true);
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
    public UserDetails signIn(String username) {
        return loadUserByUsername(username);
    }
}
