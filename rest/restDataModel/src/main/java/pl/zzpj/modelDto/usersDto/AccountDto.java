package pl.zzpj.modelDto.usersDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AccountDto {

    private Long id;
    private String login;

    @ToString.Exclude
    private String password;

    private String firstName;
    private String lastName;
}
