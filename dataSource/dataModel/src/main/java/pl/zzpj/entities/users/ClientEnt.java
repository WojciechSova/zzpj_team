package pl.zzpj.entities.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.zzpj.entities.CurrencyEnt;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class ClientEnt extends AccountEnt {

    private String accountNumber;
    private double accountState;
    private double debt;
    private CurrencyEnt currency;

    public ClientEnt(Long id, String login, String password, String firstName, String lastName,
                     String accountNumber, double accountState, double debt, CurrencyEnt currency) {
        super(id, login, password, firstName, lastName);
        this.accountNumber = accountNumber;
        this.accountState = accountState;
        this.debt = debt;
        this.currency = currency;
    }
}
