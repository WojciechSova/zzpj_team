package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

abstract class LoanCalcStrategy {
    public abstract BigDecimal calculate(List<Transaction> inputTransactions,
                                         List<Transaction> outputTransactions,
                                         List<Transaction> loanTakenTransactions,
                                         List<Transaction> loanPaidTransactions,
                                         List<Transaction> deposits,
                                         List<Transaction> withdrawals,
                                         Account account);

    protected BigDecimal calcAvgMonthlyAmount(Map<Transaction, BigDecimal> transactions) {
        BigDecimal amount = BigDecimal.ZERO;

        for (Transaction transaction : transactions.keySet()) {
            amount = amount.add(transaction.getAmount().multiply(transactions.get(transaction)));
        }
        return amount.divide(BigDecimal.valueOf(6), new MathContext(5));
    }

    protected BigDecimal calcAmount(BigDecimal input, BigDecimal output) {
        BigDecimal amount = input.multiply(BigDecimal.valueOf(1.5)).subtract(output).multiply(BigDecimal.valueOf(15));
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return amount;
        }
        return BigDecimal.ZERO;
    }

}
