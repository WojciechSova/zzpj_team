package pl.zzpj.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.adapters.AccountRestAdapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
}
