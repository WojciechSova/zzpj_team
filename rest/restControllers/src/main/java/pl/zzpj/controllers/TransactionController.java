package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(path = "maxLoan")
    public BigDecimal maxLoan(Principal principal) {
        return transactionRestAdapter.getMaxLoanAmount(principal.getName());
    }

    @CrossOrigin
    @PostMapping(path = "loan")
    public void takeLoan(@RequestBody String amount, Principal principal) throws LoanNotAvailableRestException {
        transactionRestAdapter.takeLoan(principal.getName(), new BigDecimal(amount));
    }

    @CrossOrigin
    @PostMapping(path = "payBackLoan")
    public void payBackLoan(@RequestBody String amount, Principal principal) {
        transactionRestAdapter.payBackLoan(principal.getName(), amount);
    }

    @CrossOrigin
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody String amount, Principal principal) {
        transactionRestAdapter.withdraw(amount, principal.getName());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody String amount, Principal principal) {
        transactionRestAdapter.deposit(amount, principal.getName());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping("/transfer/{accountNumber}")
    public ResponseEntity<?> transfer(@PathVariable String accountNumber, @RequestBody String amount, Principal principal) throws Exception {
        transactionRestAdapter.transfer(principal.getName(), accountNumber, amount);
        return ResponseEntity.ok().build();
    }
}
