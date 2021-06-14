package pl.zzpj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigDecimal accountState;
    private BigDecimal debt;
    private CurrencyEnt currency;
    @ManyToOne
    @JoinColumn(name = "access_level", referencedColumnName = "id")
    private AccessLevelEnt accessLevel;
    private Boolean active;
}
