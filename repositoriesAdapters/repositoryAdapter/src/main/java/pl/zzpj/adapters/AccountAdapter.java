package pl.zzpj.adapters;

import org.springframework.stereotype.Repository;
import pl.zzpj.infrastructure.AccountCRUDPort;
import pl.zzpj.model.users.Account;

import java.util.List;

@Repository
public class AccountAdapter implements AccountCRUDPort {

    @Override
    public void addAccount(Account account) {

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
        return null;
    }
}
