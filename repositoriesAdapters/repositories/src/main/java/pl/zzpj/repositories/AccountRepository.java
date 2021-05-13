package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zzpj.modelEnt.usersEnt.AccountEnt;

public interface AccountRepository extends JpaRepository<AccountEnt, Long> {
}
