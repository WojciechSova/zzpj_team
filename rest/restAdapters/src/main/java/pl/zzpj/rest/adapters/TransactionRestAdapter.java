package pl.zzpj.rest.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.controller.TransactionUseCase;
import pl.zzpj.dto.TransactionDto;
import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.exceptions.LoanNotAvailableRestException;
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

    public void takeLoan(String login, BigDecimal amount) throws LoanNotAvailableRestException {
        try {
            transactionUseCase.takeLoan(login, amount);
        } catch (LoanNotAvailableException e) {
            LoanNotAvailableRestException loanNotAvailableRestException = new LoanNotAvailableRestException(e.getMessage());
            loanNotAvailableRestException.setStackTrace(e.getStackTrace());
            throw loanNotAvailableRestException;
        }
    }
}
