package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotTrustedStrategy extends LoanCalcStrategy {

    Map<Account, List<Transaction>> inTransactionsByAccount = new HashMap<>();
    Map<Account, List<Transaction>> outTransactionsByAccount = new HashMap<>();

    @Override
    public BigDecimal calculate(List<Transaction> inputTransactions,
                                List<Transaction> outputTransactions,
                                List<Transaction> loanTakenTransactions,
                                List<Transaction> loanPaidTransactions,
                                List<Transaction> deposits,
                                List<Transaction> withdrawals,
                                Account account) {

        fillAccountMaps(inputTransactions, outputTransactions);

        fillLoansAndNonTransfers(loanTakenTransactions,
                loanPaidTransactions,
                deposits,
                withdrawals);

        for (Transaction inputTransaction : inputTransactions) {
            inMap.put(inputTransaction, calcInTransactionMultiplier(inputTransaction));
        }
        for (Transaction outputTransaction : outputTransactions) {
            outMap.put(outputTransaction, calcOutTransactionMultiplier(outputTransaction));
        }

        BigDecimal inputs = calcAvgMonthlyAmount(inMap);
        BigDecimal outputs = calcAvgMonthlyAmount(outMap);

        return calcAmount(inputs, outputs, account.getDebt());
    }

    private void fillAccountMaps(List<Transaction> inputTransactions, List<Transaction> outputTransactions) {
        for (Transaction inputTransaction : inputTransactions) {
            if (inTransactionsByAccount.containsKey(inputTransaction.getFrom())) {
                inTransactionsByAccount.get(inputTransaction.getFrom()).add(inputTransaction);
            } else {
                List<Transaction> transactionList = new ArrayList<>();
                transactionList.add(inputTransaction);
                inTransactionsByAccount.put(inputTransaction.getFrom(), transactionList);
            }
        }

        for (Transaction outputTransaction : outputTransactions) {
            if (outTransactionsByAccount.containsKey(outputTransaction.getTo())) {
                outTransactionsByAccount.get(outputTransaction.getTo()).add(outputTransaction);
            } else {
                List<Transaction> transactionList = new ArrayList<>();
                transactionList.add(outputTransaction);
                outTransactionsByAccount.put(outputTransaction.getTo(), transactionList);
            }
        }
    }

    private BigDecimal calcInTransactionMultiplier(Transaction transaction) {
        if (!transaction.getFrom().getActive()) {
            return BigDecimal.valueOf(0.2);
        }
        return getRatio(transaction.getFrom());
    }

    private BigDecimal calcOutTransactionMultiplier(Transaction transaction) {
        if (!transaction.getTo().getActive()) {
            return BigDecimal.valueOf(1.1);
        }
        return getRatio(transaction.getTo());
    }

    private BigDecimal getRatio(Account account) {
        BigDecimal inFromAccount;
        if (inTransactionsByAccount.containsKey(account)) {
            inFromAccount = inTransactionsByAccount.get(account).stream()
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        } else {
            return BigDecimal.ONE;
        }
        BigDecimal outFromAccount;
        if (outTransactionsByAccount.containsKey(account)) {
            outFromAccount = outTransactionsByAccount.get(account).stream()
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        } else {
            return BigDecimal.ONE;
        }
        return inFromAccount.divide(inFromAccount.add(outFromAccount), new MathContext(5));
    }
}
