package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
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

        Map<Transaction, BigDecimal> inMap = new HashMap<>();
        Map<Transaction, BigDecimal> outMap = new HashMap<>();
        for (Transaction loanTakenTransaction : loanTakenTransactions) {
            outMap.put(loanTakenTransaction, BigDecimal.valueOf(2.));
        }
        for (Transaction loanPaidTransaction : loanPaidTransactions) {
            inMap.put(loanPaidTransaction, BigDecimal.valueOf(2.));
        }
        for (Transaction deposit : deposits) {
            inMap.put(deposit, BigDecimal.valueOf(1.));
        }
        for (Transaction withdrawal : withdrawals) {
            outMap.put(withdrawal, BigDecimal.valueOf(1.));
        }
        for (Transaction inputTransaction : inputTransactions) {
            outMap.put(inputTransaction, calcInTransactionMultiplier(inputTransaction));
        }
        for (Transaction outputTransaction : outputTransactions) {
            outMap.put(outputTransaction, calcOutTransactionMultiplier(outputTransaction));
        }

        BigDecimal inputs = calcAvgMonthlyAmount(inMap);
        BigDecimal outputs =  calcAvgMonthlyAmount(outMap);

        return calcAmount(inputs, outputs);
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
        BigDecimal inFromAccount = inTransactionsByAccount.get(account).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        BigDecimal outFromAccount = outTransactionsByAccount.get(account).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        return inFromAccount.divide(inFromAccount.add(outFromAccount));
    }
}
