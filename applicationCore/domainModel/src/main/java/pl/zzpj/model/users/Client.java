package pl.zzpj.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.zzpj.model.Currency;

@Data
@EqualsAndHashCode(callSuper = true)
public class Client extends Account {

    private String accountNumber;
    private double accountState;
    private double debt;
    private Currency currency;
}
