package pl.zzpj.rest.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.controller.AccountCRUDUseCase;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.rest.mappers.AccountMapper;

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
}
