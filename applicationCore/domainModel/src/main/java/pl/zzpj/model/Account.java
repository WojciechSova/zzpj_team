package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private BigDecimal accountState;
    private BigDecimal debt;
    private Currency currency;
    private AccessLevel accessLevel;
    private Boolean active;
}
