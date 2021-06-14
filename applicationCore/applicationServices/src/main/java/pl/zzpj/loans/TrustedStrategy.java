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
        return BigDecimal.valueOf(2000);
    }
}
