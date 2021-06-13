package pl.zzpj.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoanCalculator {

    private final BigDecimal MIN_TRUSTED_LOAN = BigDecimal.valueOf(5000);
    private final BigDecimal MIN_TRUSTED_PAID_LOAN = BigDecimal.valueOf(2750);

    private final TransactionPort transactionPort;
    private LoanCalcStrategy loanCalcStrategy;

    @Autowired
    public LoanCalculator(TransactionPort transactionPort) {
        this.transactionPort = transactionPort;
    }

    public BigDecimal calculateMaxLoanAmount(Account account) {
        List<Transaction> transactions = transactionPort.findAllByAccount(account);

        List<Transaction> loanTransactions = transactions.stream()
                .filter(Transaction::getIsLoan)
                .collect(Collectors.toList());

        BigDecimal loansTakenAmount = loanTransactions.stream()
                .filter(transaction -> transaction.getFrom() == null)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal loansPaidAmount = loanTransactions.stream()
                .filter(transaction -> transaction.getTo() == null)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (loansTakenAmount.compareTo(MIN_TRUSTED_LOAN) > 0
        && loansPaidAmount.compareTo(MIN_TRUSTED_PAID_LOAN) > 0) {
            loanCalcStrategy = new TrustedStrategy();
        } else {
            loanCalcStrategy = new NotTrustedStrategy();
        }

        return BigDecimal.valueOf(2000L);
    }
}
