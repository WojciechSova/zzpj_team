package pl.zzpj.rest.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.mappers.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountRestAdapter {
    private final AccountCRUDUseCase accountCRUDUseCase;

    @Autowired
    public AccountRestAdapter(AccountCRUDUseCase accountCRUDUseCase) {
        this.accountCRUDUseCase = accountCRUDUseCase;
    }

    public void addAccount(AccountDto accountDto) {
        accountCRUDUseCase.addAccount(AccountMapper.mapToAccount(accountDto));
    }

    public AccountDto findByLogin(String login) {
        return AccountMapper.mapToAccountDto(accountCRUDUseCase.findByLogin(login));
    }

    public List<AccountDto> findAll() {
        return accountCRUDUseCase.findAll().stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    public void editAccount(String login, AccountDto accountDto) {
        accountCRUDUseCase.updateAccount(login, AccountMapper.mapToAccount(accountDto));
    }

    public void blockAccount(String login) {
        accountCRUDUseCase.blockAccount(login);
    }

    public void unblockAccount(String login) {
        accountCRUDUseCase.unblockAccount(login);
    }
}
