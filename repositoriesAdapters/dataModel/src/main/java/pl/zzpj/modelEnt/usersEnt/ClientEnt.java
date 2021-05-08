package pl.zzpj.modelEnt.usersEnt;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.zzpj.modelEnt.CurrencyEnt;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientEnt extends AccountEnt {

    private String accountNumber;
    private double accountState;
    private double debt;
    private CurrencyEnt currency;

    public ClientEnt(UUID uuid, String login, String password, String firstName, String lastName,
                     String accountNumber, double accountState, double debt, CurrencyEnt currency) {
        super(uuid, login, password, firstName, lastName);
        this.accountNumber = accountNumber;
        this.accountState = accountState;
        this.debt = debt;
        this.currency = currency;
    }
}
