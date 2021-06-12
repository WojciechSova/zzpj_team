package pl.zzpj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private AccountEnt fromId;

    private CurrencyEnt fromCurrency;

    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private AccountEnt toId;

    private CurrencyEnt toCurrency;
    private BigDecimal amount;
    private Timestamp date;
    private BigDecimal rate;
}
