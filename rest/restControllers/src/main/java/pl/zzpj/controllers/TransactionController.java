package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.dto.TransactionDto;
import pl.zzpj.rest.adapters.TransactionRestAdapter;

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
}
