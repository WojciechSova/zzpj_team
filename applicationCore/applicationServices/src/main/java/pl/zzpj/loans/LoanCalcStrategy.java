package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class LoanCalcStrategy {

    Map<Transaction, BigDecimal> inMap = new HashMap<>();
    Map<Transaction, BigDecimal> outMap = new HashMap<>();

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

    protected BigDecimal calcAmount(BigDecimal input, BigDecimal output, BigDecimal debt) {
        BigDecimal amount = input
                .multiply(BigDecimal.valueOf(1.2))
                .subtract(output)
                .subtract(debt)
                .multiply(BigDecimal.valueOf(15));
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return amount;
        }
        return BigDecimal.ZERO;
    }

    protected void fillLoansAndNonTransfers(List<Transaction> loanTakenTransactions,
                                            List<Transaction> loanPaidTransactions,
                                            List<Transaction> deposits,
                                            List<Transaction> withdrawals) {
        for (Transaction loanTakenTransaction : loanTakenTransactions) {
            outMap.put(loanTakenTransaction, BigDecimal.valueOf(2.));
        }
        for (Transaction loanPaidTransaction : loanPaidTransactions) {
            inMap.put(loanPaidTransaction, BigDecimal.valueOf(1.4));
        }
        for (Transaction deposit : deposits) {
            inMap.put(deposit, BigDecimal.valueOf(1.));
        }
        for (Transaction withdrawal : withdrawals) {
            outMap.put(withdrawal, BigDecimal.valueOf(1.));
        }
    }

}
