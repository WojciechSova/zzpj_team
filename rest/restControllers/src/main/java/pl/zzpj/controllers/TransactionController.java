package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.dto.TransactionDto;
import pl.zzpj.exceptions.LoanNotAvailableRestException;
import pl.zzpj.rest.adapters.TransactionRestAdapter;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRestAdapter transactionRestAdapter;

    @Autowired
    public TransactionController(TransactionRestAdapter transactionRestAdapter) {
        this.transactionRestAdapter = transactionRestAdapter;
    }

    @CrossOrigin
    @GetMapping
    public List<TransactionDto> getTransactions() {
        return transactionRestAdapter.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "loan")
    public void takeLoan(String amount, Principal principal) throws LoanNotAvailableRestException {
        transactionRestAdapter.takeLoan(principal.getName(), new BigDecimal(amount));
    }
}
