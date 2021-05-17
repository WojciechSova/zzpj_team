package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.dto.AccessLevelDto;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.dto.CurrencyDto;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    @Test
    void mapToAccountDto() {
        Account account = new Account();
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setLevel("ADMIN");

        account.setAccessLevel(accessLevel);
        account.setLogin("clogin");
        account.setId(10L);
        account.setFirstName("cFirstName");
        account.setLastName("cLastName");
        account.setPassword("cPassword");
        account.setAccountNumber("cNumber");
        account.setAccountState(200.);
        account.setDebt(500.);
        account.setCurrency(Currency.EUR);

        AccountDto accountDto = AccountMapper.mapToAccountDto(account);

        assertNotNull(accountDto.getAccessLevel());
        assertEquals("ADMIN", accountDto.getAccessLevel().getLevel());

        assertEquals("clogin", accountDto.getLogin());
        assertEquals(10L, accountDto.getId());
        assertEquals("cFirstName", accountDto.getFirstName());
        assertEquals("cLastName", accountDto.getLastName());
        assertEquals("cPassword", accountDto.getPassword());
        assertEquals("cNumber", accountDto.getAccountNumber());
        assertEquals(200, accountDto.getAccountState());
        assertEquals(500, accountDto.getDebt());
        assertEquals(CurrencyDto.EUR, accountDto.getCurrency());
    }

    @Test
    void mapToAccount() {
        AccountDto accountDto = new AccountDto();

        AccessLevelDto accessLevel = new AccessLevelDto();
        accessLevel.setLevel("ADMIN");

        accountDto.setAccessLevel(accessLevel);
        accountDto.setLogin("clogin");
        accountDto.setId(10L);
        accountDto.setFirstName("cFirstName");
        accountDto.setLastName("cLastName");
        accountDto.setPassword("cPassword");
        accountDto.setAccountNumber("cNumber");
        accountDto.setAccountState(200.);
        accountDto.setDebt(500.);
        accountDto.setCurrency(CurrencyDto.EUR);

        Account account = AccountMapper.mapToAccount(accountDto);

        assertNotNull(account.getAccessLevel());
        assertEquals("ADMIN", account.getAccessLevel().getLevel());
        assertEquals("clogin", account.getLogin());
        assertEquals(10L, account.getId());
        assertEquals("cFirstName", account.getFirstName());
        assertEquals("cLastName", account.getLastName());
        assertEquals("cPassword", account.getPassword());
        assertEquals("cNumber", account.getAccountNumber());
        assertEquals(200, account.getAccountState());
        assertEquals(500, account.getDebt());
        assertEquals(Currency.EUR, account.getCurrency());
    }
}
