package pl.zzpj.modelDto.usersDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.zzpj.modelDto.CurrencyDto;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends AccountDto {

    private String accountNumber;
    private double accountState;
    private double debt;
    private CurrencyDto currency;

    public ClientDto(UUID uuid, String login, String password, String firstName, String lastName) {
        super(uuid, login, password, firstName, lastName);
    }
}
