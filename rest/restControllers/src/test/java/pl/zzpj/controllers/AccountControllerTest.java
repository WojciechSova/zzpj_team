package pl.zzpj.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.model.Account;
import pl.zzpj.rest.adapters.AccountRestAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountRestAdapter accountRestAdapter;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAccount() {
        AccountDto accountDto = new AccountDto();
        String login = "login";
        accountDto.setLogin(login);
        when(accountRestAdapter.findByLogin(login)).thenReturn(accountDto);
        assertEquals(accountDto, accountController.getAccount(login));
    }

    @Test
    void getAccounts() {
        AccountDto accountDto = new AccountDto();
        AccountDto accountDto2 = new AccountDto();
        String login = "login";
        String login2 = "login2";
        accountDto.setLogin(login);
        accountDto2.setLogin(login2);
        when(accountRestAdapter.findAll()).thenReturn(Arrays.asList(accountDto, accountDto2));
        assertEquals(Arrays.asList(accountDto, accountDto2), accountController.getAccounts());
    }

    @Test
    void addAccount() {
        AccountDto accountDto = new AccountDto();
        String login = "login";
        accountDto.setLogin(login);

        List<AccountDto> accountDtos = new ArrayList<>();

        doAnswer(invocationOnMock -> {
            accountDtos.add(accountDto);
            return null;
        }).when(accountRestAdapter).addAccount(accountDto);

        accountController.addAccount(accountDto);

        assertEquals(1, accountDtos.size());
    }

    @Test
    void editAccount() {
        AccountDto accountDto = new AccountDto();
        AccountDto accountDto2 = new AccountDto();
        String login = "login";
        accountDto.setFirstName("firstName");

        doAnswer(invocationOnMock -> {
            accountDto2.setFirstName(accountDto.getFirstName());
            return null;
        }).when(accountRestAdapter).editAccount(login, accountDto);

        accountController.editAccount(login, accountDto);

        assertEquals(accountDto.getFirstName(), accountDto2.getFirstName());
    }

    @Test
    void blockAccount() {
        accountController.blockAccount("login");
        verify(accountRestAdapter).blockAccount("login");
    }

    @Test
    void unblockAccount() {
        accountController.unblockAccount("login");
        verify(accountRestAdapter).unblockAccount("login");
    }
}
