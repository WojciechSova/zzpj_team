package pl.zzpj.modelEnt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.modelEnt.usersEnt.ClientEnt;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionEnt {

    @Id
    private Long id;

    @ManyToOne
    private ClientEnt from;

    @ManyToOne
    private ClientEnt to;
    private double amount;
    private Timestamp date;
}
