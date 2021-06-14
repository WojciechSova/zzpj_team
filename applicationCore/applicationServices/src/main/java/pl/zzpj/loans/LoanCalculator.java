package pl.zzpj.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.zzpj.infrastructure.TransactionPort;
import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoanCalculator {

    private final BigDecimal MIN_TRUSTED_LOAN = BigDecimal.valueOf(50000);
    private final BigDecimal MIN_TRUSTED_PAID_LOAN = BigDecimal.valueOf(27500);

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

        transactions = getLastSixMonths(transactions);

        List<Transaction> inputTransactions = transactions.stream()
                .filter(transaction -> !transaction.getIsLoan())
                .filter(transaction -> transaction.getTo() != null
                        && transaction.getTo().equals(account)
                        && transaction.getFrom() != null)
                .collect(Collectors.toList());

        List<Transaction> deposits = transactions.stream()
                .filter(transaction -> !transaction.getIsLoan())
                .filter(transaction -> transaction.getTo() != null
                        && transaction.getTo().equals(account)
                        && transaction.getFrom() == null)
                .collect(Collectors.toList());

        List<Transaction> outputTransactions = transactions.stream()
                .filter(transaction -> !transaction.getIsLoan())
                .filter(transaction -> transaction.getFrom() != null
                        && transaction.getFrom().equals(account)
                        && transaction.getTo() != null)
                .collect(Collectors.toList());

        List<Transaction> withdrawals = transactions.stream()
                .filter(transaction -> !transaction.getIsLoan())
                .filter(transaction -> transaction.getFrom() != null
                        && transaction.getFrom().equals(account)
                        && transaction.getTo() == null)
                .collect(Collectors.toList());

        List<Transaction> loanPaidTransactions = transactions.stream()
                .filter(Transaction::getIsLoan)
                .filter(transaction -> transaction.getFrom() != null
                        && transaction.getFrom().equals(account))
                .collect(Collectors.toList());

        List<Transaction> loanTakenTransactions = transactions.stream()
                .filter(Transaction::getIsLoan)
                .filter(transaction -> transaction.getTo() != null
                        && transaction.getTo().equals(account))
                .collect(Collectors.toList());

        return loanCalcStrategy.calculate(inputTransactions, outputTransactions, loanTakenTransactions,
                loanPaidTransactions, deposits, withdrawals, account);
    }

    private List<Transaction> getLastSixMonths(List<Transaction> transactions) {
        return transactions.stream()
                .filter(
                        transaction -> transaction.getDate()
                                .after(Timestamp.from(Instant.now().minus(180, ChronoUnit.DAYS)))
                ).collect(Collectors.toList());
    }
}
