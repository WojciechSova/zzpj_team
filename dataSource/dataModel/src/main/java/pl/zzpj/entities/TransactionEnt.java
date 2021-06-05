package pl.zzpj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEnt {

    @Id
    private Long id;

    @ManyToOne
    private AccountEnt from;

    @ManyToOne
    private AccountEnt to;
    private double amount;
    private Timestamp date;
    private BigDecimal rate;
}
