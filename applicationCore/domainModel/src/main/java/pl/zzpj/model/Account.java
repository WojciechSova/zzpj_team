package pl.zzpj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
