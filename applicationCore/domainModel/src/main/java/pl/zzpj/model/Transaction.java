package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Transaction {

    private Account from;
    private Account to;
    private double amount;
    private Timestamp date;
}
