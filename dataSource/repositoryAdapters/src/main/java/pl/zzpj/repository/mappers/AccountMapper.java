package pl.zzpj.repository.mappers;

import pl.zzpj.model.users.Account;
import pl.zzpj.model.users.Admin;
import pl.zzpj.model.users.Client;
import pl.zzpj.model.users.Employee;
import pl.zzpj.entities.users.AccountEnt;
import pl.zzpj.entities.users.AdminEnt;
import pl.zzpj.entities.users.ClientEnt;
import pl.zzpj.entities.users.EmployeeEnt;

public class AccountMapper {

    public static AccountEnt mapToAccountEnt(Account account) {
        AccountEnt accountEnt;

        if (account instanceof Client) {
            accountEnt = new ClientEnt();
        } else if (account instanceof Admin) {
            accountEnt = new AdminEnt();
        } else {
            accountEnt = new EmployeeEnt();
        }

        accountEnt.setFirstName(account.getFirstName());
        accountEnt.setLastName(account.getLastName());
        accountEnt.setLogin(account.getLogin());
        accountEnt.setPassword(account.getPassword());
        accountEnt.setId(account.getId());

        return accountEnt;
    }

    public static Account mapToAccount(AccountEnt accountEnt) {
        Account account;

        if (accountEnt instanceof ClientEnt) {
            account = new Client();
        } else if (accountEnt instanceof AdminEnt) {
            account = new Admin();
        } else {
            account = new Employee();
        }

        account.setFirstName(accountEnt.getFirstName());
        account.setLastName(accountEnt.getLastName());
        account.setLogin(accountEnt.getLogin());
        account.setId(accountEnt.getId());
        account.setPassword(account.getPassword());

        return account;
    }
}
