package pl.zzpj.modelEnt.usersEnt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AccountEnt {

    private UUID uuid;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
