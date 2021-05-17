package pl.zzpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private AccountDto from;
    private AccountDto to;
    private double amount;
    private Timestamp date;

}
