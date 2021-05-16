package pl.zzpj.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String login;

    @ToString.Exclude
    private String password;

    private String firstName;
    private String lastName;
    private String accountNumber;
    private Double accountState;
    private Double debt;
    private CurrencyDto currency;
    private AccessLevelDto accessLevel;
}