package pl.zzpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private AccountDto from;
    private CurrencyDto fromCurrency;
    private AccountDto to;
    private CurrencyDto toCurrency;
    private BigDecimal amount;
    private Timestamp date;
    private BigDecimal rate;
}
