package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
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
                                List<Transaction> loanPaidTransactions) {
        Map<Transaction, Double> inMap = new HashMap<>();



//        for (Transaction inputTransaction : ) {
//            inMap.put(inputTransaction, 0.5);
//        }

        return calcAvgMonthlyAmount(inMap);
    }
}
