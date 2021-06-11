package pl.zzpj.infrastructure;

import pl.zzpj.model.AccessLevel;

public interface AccessLevelCRUDPort {

    AccessLevel findByLevel(String level);
}
