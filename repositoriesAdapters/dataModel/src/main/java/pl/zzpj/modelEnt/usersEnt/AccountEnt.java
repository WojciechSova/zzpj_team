package pl.zzpj.modelEnt.usersEnt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AccountEnt {

    @Id
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
