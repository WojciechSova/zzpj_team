package pl.zzpj.loans;

import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class NotTrustedStrategy implements LoanCalcStrategy {
    @Override
    public BigDecimal calculate(List<Transaction> transactions) {
        return null;
    }
}
