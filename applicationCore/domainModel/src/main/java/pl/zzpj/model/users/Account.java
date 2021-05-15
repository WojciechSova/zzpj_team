package pl.zzpj.model.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class Account {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
