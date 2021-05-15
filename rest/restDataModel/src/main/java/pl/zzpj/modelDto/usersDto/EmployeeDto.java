package pl.zzpj.modelDto.usersDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends AccountDto {

    public EmployeeDto(Long id, String login, String password, String firstName, String lastName) {
        super(id, login, password, firstName, lastName);
    }
}
