package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Transaction {

    private Account from;
    private Currency fromCurrency;
    private Account to;
    private Currency toCurrency;
    private BigDecimal amount;
    private Timestamp date;
    private BigDecimal rate;
}
