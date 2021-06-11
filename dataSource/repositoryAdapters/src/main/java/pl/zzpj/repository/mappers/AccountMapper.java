package pl.zzpj.repository.mappers;

import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;

public class AccountMapper {

    public static AccountEnt mapToAccountEnt(Account account) {
        AccountEnt accountEnt = new AccountEnt();

        accountEnt.setFirstName(account.getFirstName());
        accountEnt.setLastName(account.getLastName());
        accountEnt.setLogin(account.getLogin());
        accountEnt.setPassword(account.getPassword());
        accountEnt.setId(account.getId());
        accountEnt.setAccountNumber(account.getAccountNumber());
        accountEnt.setAccountState(account.getAccountState());
        accountEnt.setActive(account.getActive());
        accountEnt.setDebt(account.getDebt());
        if (account.getCurrency() != null) {
            accountEnt.setCurrency(CurrencyMapper.mapToCurrencyEnt(account.getCurrency()));
        }
        AccessLevelEnt al = new AccessLevelEnt();
        al.setLevel(account.getAccessLevel().getLevel());
        al.setId(account.getAccessLevel().getId());
        accountEnt.setAccessLevel(al);
        return accountEnt;
    }

    public static Account mapToAccount(AccountEnt accountEnt) {
        Account account = new Account();

        account.setFirstName(accountEnt.getFirstName());
        account.setLastName(accountEnt.getLastName());
        account.setLogin(accountEnt.getLogin());
        account.setId(accountEnt.getId());
        account.setPassword(accountEnt.getPassword());
        account.setAccountNumber(accountEnt.getAccountNumber());
        account.setAccountState(accountEnt.getAccountState());
        account.setActive(accountEnt.getActive());
        account.setDebt(accountEnt.getDebt());
        if (accountEnt.getCurrency() != null) {
            account.setCurrency(CurrencyMapper.mapToCurrency(accountEnt.getCurrency()));
        }
        AccessLevel al = new AccessLevel();
        al.setLevel(accountEnt.getAccessLevel().getLevel());
        al.setId(accountEnt.getAccessLevel().getId());
        account.setAccessLevel(al);
        return account;
    }
}
