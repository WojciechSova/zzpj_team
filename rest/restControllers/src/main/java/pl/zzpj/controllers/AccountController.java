package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.mappers.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountCRUDUseCase accountCRUDUseCase;

    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountCRUDUseCase.findAll().stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }
}
