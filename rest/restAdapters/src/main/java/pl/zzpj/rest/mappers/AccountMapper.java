package pl.zzpj.rest.mappers;

import pl.zzpj.model.users.Account;
import pl.zzpj.model.users.Admin;
import pl.zzpj.model.users.Client;
import pl.zzpj.model.users.Employee;
import pl.zzpj.modelDto.usersDto.AccountDto;
import pl.zzpj.modelDto.usersDto.AdminDto;
import pl.zzpj.modelDto.usersDto.ClientDto;
import pl.zzpj.modelDto.usersDto.EmployeeDto;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto;

        if (account instanceof Client) {
            Client client = (Client) account;
            ClientDto clientDto = new ClientDto();
            clientDto.setCurrency(
                    CurrencyMapper.mapToCurrencyDto(client.getCurrency())
            );
            clientDto.setAccountNumber(client.getAccountNumber());
            clientDto.setAccountState(client.getAccountState());
            clientDto.setDebt(client.getDebt());

            accountDto = clientDto;
        } else if (account instanceof Admin) {
            accountDto = new AdminDto();
        } else {
            accountDto = new EmployeeDto();
        }

        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setLogin(account.getLogin());
        accountDto.setPassword(account.getPassword());
        accountDto.setId(account.getId());

        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto) {
        Account account;

        if (accountDto instanceof ClientDto) {
            ClientDto clientDto = (ClientDto) accountDto;
            Client client = new Client();
            client.setCurrency(
                    CurrencyMapper.mapToCurrency(clientDto.getCurrency())
            );
            client.setAccountNumber(clientDto.getAccountNumber());
            client.setAccountState(clientDto.getAccountState());
            client.setDebt(clientDto.getDebt());

            account = client;
        } else if (accountDto instanceof AdminDto) {
            account = new Admin();
        } else {
            account = new Employee();
        }

        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setLogin(accountDto.getLogin());
        account.setId(accountDto.getId());
        account.setPassword(accountDto.getPassword());

        return account;
    }
}
