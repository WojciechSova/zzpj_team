package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private Double accountState;
    private Double debt;
    private Currency currency;
    private AccessLevel accessLevel;
}
