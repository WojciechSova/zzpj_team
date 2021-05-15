package pl.zzpj.controller;

import pl.zzpj.model.users.Account;

import java.util.List;

public interface AccountCRUDUseCase {

    void addAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(String login, Account account);

    Account findByLogin(String login);

    List<Account> findAll();
}
