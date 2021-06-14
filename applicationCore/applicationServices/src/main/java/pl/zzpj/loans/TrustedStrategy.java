package pl.zzpj.loans;

import pl.zzpj.model.Account;
import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class TrustedStrategy extends LoanCalcStrategy {
    @Override
    public BigDecimal calculate(List<Transaction> inputTransactions,
                                List<Transaction> outputTransactions,
                                List<Transaction> loanTakenTransactions,
                                List<Transaction> loanPaidTransactions,
                                List<Transaction> deposits,
                                List<Transaction> withdrawals,
                                Account account) {

        fillLoansAndNonTransfers(loanTakenTransactions,
                loanPaidTransactions,
                deposits,
                withdrawals);

        for (Transaction inputTransaction : inputTransactions) {
            inMap.put(inputTransaction, BigDecimal.valueOf(1.));
        }
        for (Transaction outputTransaction : outputTransactions) {
            outMap.put(outputTransaction, BigDecimal.valueOf(1.));
        }

        BigDecimal inputs = calcAvgMonthlyAmount(inMap);
        BigDecimal outputs =  calcAvgMonthlyAmount(outMap);

        return calcAmount(inputs, outputs, account.getDebt());
    }
}
