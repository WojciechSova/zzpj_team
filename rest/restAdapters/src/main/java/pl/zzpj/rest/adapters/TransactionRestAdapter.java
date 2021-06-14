package pl.zzpj.rest.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.controller.TransactionUseCase;
import pl.zzpj.dto.TransactionDto;
import pl.zzpj.rest.mappers.TransactionMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransactionRestAdapter {

    private final TransactionUseCase transactionUseCase;

    @Autowired
    public TransactionRestAdapter(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    public List<TransactionDto> findAll() {
        return transactionUseCase.findAll().stream().map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }

    public void withdraw(String amount, String login) {
        transactionUseCase.withdraw(login, new BigDecimal(amount));
    }

    public void deposit(String amount, String login) {
        transactionUseCase.deposit(login, new BigDecimal(amount));
    }

    public void transfer(String loginFrom, String accountNumberTo, String amount) throws Exception {
        transactionUseCase.transfer(loginFrom, accountNumberTo, new BigDecimal(amount));
    }
}
