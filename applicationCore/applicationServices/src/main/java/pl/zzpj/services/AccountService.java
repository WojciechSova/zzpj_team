package pl.zzpj.services;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.controller.SignInUseCase;
import pl.zzpj.infrastructure.AccessLevelCRUDPort;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.UserPrincipal;
import pl.zzpj.model.Account;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements AccountCRUDUseCase, SignInUseCase {

    private final AccountCRUDPort accountCRUDPort;
    private final AccessLevelCRUDPort accessLevelCRUDPort;

    @Autowired
    public AccountService(AccountCRUDPort accountCRUDPort, AccessLevelCRUDPort accessLevelCRUDPort) {
        this.accountCRUDPort = accountCRUDPort;
        this.accessLevelCRUDPort = accessLevelCRUDPort;
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
        Account acc = accountCRUDPort.findByLogin(login);
        AccessLevel accLvl = accessLevelCRUDPort.findByLevel(account.getAccessLevel().getLevel());
        if (account.getPassword() != null && !account.getPassword().isBlank()) {
            acc.setPassword(account.getPassword());
        }
        acc.setFirstName(account.getFirstName());
        acc.setLastName(account.getLastName());
        acc.setAccessLevel(accLvl);
        accountCRUDPort.updateAccount(acc);
    }

    @Override
    public void blockAccount(String login) {
        Account account = accountCRUDPort.findByLogin(login);
        account.setActive(false);
        accountCRUDPort.updateAccount(account);
    }

    @Override
    public void unblockAccount(String login) {
        Account account = accountCRUDPort.findByLogin(login);
        account.setActive(true);
        accountCRUDPort.updateAccount(account);
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
