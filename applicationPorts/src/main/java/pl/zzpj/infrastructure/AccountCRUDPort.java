package pl.zzpj.infrastructure;

import pl.zzpj.model.users.Account;

import java.util.List;

public interface AccountCRUDPort {

    void addAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(String login, Account account);

    Account findByLogin(String login);

    List<Account> findAll();
}
