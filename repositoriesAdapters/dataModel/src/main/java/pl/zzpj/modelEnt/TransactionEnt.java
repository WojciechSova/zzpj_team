package pl.zzpj.modelEnt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.modelEnt.usersEnt.ClientEnt;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEnt {

    private ClientEnt from;
    private ClientEnt to;
    private double amount;
    private Timestamp date;
}
