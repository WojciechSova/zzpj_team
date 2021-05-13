package pl.zzpj.modelDto.usersDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends AccountDto {

    public ClientDto(UUID uuid, String login, String password, String firstName, String lastName) {
        super(uuid, login, password, firstName, lastName);
    }
}
