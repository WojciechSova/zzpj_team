package pl.zzpj.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.zzpj.model.Currency;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Account {

    private String accountNumber;
    private double accountState;
    private double debt;
    private Currency currency;
}
