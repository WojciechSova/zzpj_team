package pl.zzpj.repository.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.entities.CurrencyEnt;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    @Test
    void mapToAccountEnt() {
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

        AccountEnt accountEnt = AccountMapper.mapToAccountEnt(account);

        assertNotNull(accountEnt.getAccessLevel());
        assertEquals("ADMIN", accountEnt.getAccessLevel().getLevel());

        assertEquals("clogin", accountEnt.getLogin());
        assertEquals(10L, accountEnt.getId());
        assertEquals("cFirstName", accountEnt.getFirstName());
        assertEquals("cLastName", accountEnt.getLastName());
        assertEquals("cPassword", accountEnt.getPassword());
        assertEquals("cNumber", accountEnt.getAccountNumber());
        assertEquals(200, accountEnt.getAccountState());
        assertEquals(500, accountEnt.getDebt());
        assertEquals(CurrencyEnt.EUR, accountEnt.getCurrency());
    }

    @Test
    void mapToAccount() {
        AccountEnt accountEnt = new AccountEnt();

        AccessLevelEnt accessLevel = new AccessLevelEnt();
        accessLevel.setLevel("ADMIN");

        accountEnt.setAccessLevel(accessLevel);
        accountEnt.setLogin("clogin");
        accountEnt.setId(10L);
        accountEnt.setFirstName("cFirstName");
        accountEnt.setLastName("cLastName");
        accountEnt.setPassword("cPassword");
        accountEnt.setAccountNumber("cNumber");
        accountEnt.setAccountState(200.);
        accountEnt.setDebt(500.);
        accountEnt.setCurrency(CurrencyEnt.EUR);

        Account account = AccountMapper.mapToAccount(accountEnt);

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