package pl.zzpj.infrastructure;

import pl.zzpj.model.Account;

import java.util.List;

public interface AccountCRUDPort {

    void addAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(Account account);

    Account findByLogin(String login);

    Account findByAccountNumber(String accountNumber);

    List<Account> findAll();
}
