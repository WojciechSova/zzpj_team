package pl.zzpj.loans;

import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class LoanCalcStrategy {
    public abstract BigDecimal calculate(List<Transaction> inputTransactions,
                                         List<Transaction> outputTransactions,
                                         List<Transaction> loanTakenTransactions,
                                         List<Transaction> loanPaidTransactions);

    protected BigDecimal calcAvgMonthlyAmount(Map<Transaction, Double> transactions) {
        BigDecimal amount = BigDecimal.ZERO;

        for (Transaction transaction : transactions.keySet()) {
            amount = amount.add(transaction.getAmount().multiply(BigDecimal.valueOf(transactions.get(transaction))));
        }
        return amount.divide(BigDecimal.valueOf(6));
    }

    protected List<Transaction> getLastSixMonths(List<Transaction> transactions) {
        return transactions.stream()
                .filter(
                        transaction -> transaction.getDate()
                                .after(Timestamp.from(Instant.now().minus(180, ChronoUnit.DAYS)))
                ).collect(Collectors.toList());
    }

}
