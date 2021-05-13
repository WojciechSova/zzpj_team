package pl.zzpj.modelDto.usersDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends AccountDto {

    public EmployeeDto(UUID uuid, String login, String password, String firstName, String lastName) {
        super(uuid, login, password, firstName, lastName);
    }
}
