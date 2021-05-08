package pl.zzpj.modelEnt.usersEnt;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeEnt extends AccountEnt {

    public EmployeeEnt(UUID uuid, String login, String password, String firstName, String lastName) {
        super(uuid, login, password, firstName, lastName);
    }
}
