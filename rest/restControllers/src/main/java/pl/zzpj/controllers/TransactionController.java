package pl.zzpj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.dto.TransactionDto;
import pl.zzpj.rest.adapters.TransactionRestAdapter;
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
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody String amount, Principal principal) {
        transactionRestAdapter.withdraw(amount, principal.getName());
        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody String amount, Principal principal) {
        transactionRestAdapter.deposit(amount, principal.getName());
        return ResponseEntity.ok().body("ok");
    }

    @CrossOrigin
    @PostMapping("/transfer/{accountNumber}")
    public ResponseEntity<?> transfer(@PathVariable String accountNumber, @RequestBody String amount, Principal principal) throws Exception {
        transactionRestAdapter.transfer(principal.getName(), accountNumber, amount);
        return ResponseEntity.ok().build();
    }
}
