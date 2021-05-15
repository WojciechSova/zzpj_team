package pl.zzpj.modelDto.usersDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.zzpj.modelDto.CurrencyDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends AccountDto {

    private String accountNumber;
    private double accountState;
    private double debt;
    private CurrencyDto currency;

    public ClientDto(Long id, String login, String password, String firstName, String lastName) {
        super(id, login, password, firstName, lastName);
    }
}
