package pl.zzpj.repository.mappers;

import pl.zzpj.entities.AccessLevelEnt;
import pl.zzpj.model.AccessLevel;

public class AccessLevelMapper {

    public static AccessLevel mapToAccessLevel(AccessLevelEnt accessLevelEnt) {
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setLevel(accessLevelEnt.getLevel());
        accessLevel.setId(accessLevelEnt.getId());
        return accessLevel;
    }
}
