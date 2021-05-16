package pl.zzpj.entities.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class AdminEnt extends AccountEnt {

    public AdminEnt(Long id, String login, String password, String firstName, String lastName) {
        super(id, login, password, firstName, lastName);
    }
}
