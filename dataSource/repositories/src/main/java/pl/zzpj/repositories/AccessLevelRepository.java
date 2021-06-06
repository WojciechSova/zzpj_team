package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zzpj.entities.AccessLevelEnt;

public interface AccessLevelRepository extends JpaRepository<AccessLevelEnt, Long> {
    AccessLevelEnt findByLevel(String level);
}
