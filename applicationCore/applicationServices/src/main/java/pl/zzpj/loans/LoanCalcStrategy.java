package pl.zzpj.loans;

import pl.zzpj.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

interface LoanCalcStrategy {
    BigDecimal calculate(List<Transaction> transactions);
}
