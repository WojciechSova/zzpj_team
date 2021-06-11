package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.adapters.AccountRestAdapter;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    final AccountRestAdapter accountRestAdapter;

    @Autowired
    public AccountController(AccountRestAdapter accountRestAdapter) {
        this.accountRestAdapter = accountRestAdapter;
    }

    @CrossOrigin
    @GetMapping("/{login}")
    public AccountDto getAccount(@PathVariable String login) {
        return accountRestAdapter.findByLogin(login);
    }

    @CrossOrigin
    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountRestAdapter.findAll();
    }

    @CrossOrigin
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/edit/{login}")
    public void editAccount(@PathVariable("login") String login, @RequestBody AccountDto accountDto) {
        this.accountRestAdapter.editAccount(login, accountDto);
    }

    @CrossOrigin
    @PutMapping(path = "/block/{login}")
    public void blockAccount(@PathVariable("login") String login) {
        this.accountRestAdapter.blockAccount(login);
    }

    @CrossOrigin
    @PutMapping(path = "/unblock/{login}")
    public void unblockAccount(@PathVariable("login") String login) {
        this.accountRestAdapter.unblockAccount(login);
    }

    @CrossOrigin
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAccount(@RequestBody AccountDto accountDto) {
        accountRestAdapter.addAccount(accountDto);
    }
}
