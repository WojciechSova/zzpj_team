package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.model.users.Account;
import pl.zzpj.model.users.Client;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Transaction {

    private Client from;
    private Client to;
    private double amount;
    private Timestamp date;
}
