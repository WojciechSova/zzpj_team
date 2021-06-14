package pl.zzpj.rest.mappers;

import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.dto.AccessLevelDto;
import pl.zzpj.dto.AccountDto;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setLogin(account.getLogin());
        accountDto.setPassword(account.getPassword());
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountState(account.getAccountState());
        accountDto.setActive(account.getActive());
        accountDto.setDebt(account.getDebt());
        if (account.getCurrency() != null) {
            accountDto.setCurrency(CurrencyMapper.mapToCurrencyDto(account.getCurrency()));
        }
        AccessLevelDto al = new AccessLevelDto();
        al.setLevel(account.getAccessLevel().getLevel());
        al.setId(account.getAccessLevel().getId());
        accountDto.setAccessLevel(al);

        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        Account account = new Account();

        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setLogin(accountDto.getLogin());
        account.setId(accountDto.getId());
        account.setPassword(accountDto.getPassword());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountState(accountDto.getAccountState());
        account.setActive(accountDto.getActive());
        account.setDebt(accountDto.getDebt());

        if (accountDto.getCurrency() != null) {
            account.setCurrency(CurrencyMapper.mapToCurrency(accountDto.getCurrency()));
        }

        AccessLevel al = new AccessLevel();
        al.setLevel(accountDto.getAccessLevel().getLevel());
        al.setId(accountDto.getAccessLevel().getId());
        account.setAccessLevel(al);

        return account;
    }
}
