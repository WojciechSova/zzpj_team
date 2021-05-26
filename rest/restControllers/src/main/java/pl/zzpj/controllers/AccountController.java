package pl.zzpj.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.adapters.AccountRestAdapter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    final AccountRestAdapter accountRestAdapter;

    public AccountController(AccountRestAdapter accountRestAdapter) {
        this.accountRestAdapter = accountRestAdapter;
    }

    @GetMapping()
    public List<AccountDto> getAccounts() {
        return accountRestAdapter.findAllAccounts();
    }

    @GetMapping("/{login}")
    public AccountDto getAccount(@PathVariable String login) {
        return accountRestAdapter.findByLogin(login);
    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountRestAdapter.findAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/edit/{login}")
    public void editAccount(@PathVariable("login") String login, @RequestBody AccountDto accountDto) {
        this.accountRestAdapter.editAccount(login, accountDto);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAccount(@RequestBody AccountDto accountDto) {
        accountRestAdapter.addAccount(accountDto);
    }
}
