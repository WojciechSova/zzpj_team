package pl.zzpj.controller;

import pl.zzpj.model.Account;

import java.util.List;

public interface AccountCRUDUseCase {

    void addAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(String login, Account account);

    void blockAccount(String login);

    void unblockAccount(String login);

    Account findByLogin(String login);

    List<Account> findAll();
}
