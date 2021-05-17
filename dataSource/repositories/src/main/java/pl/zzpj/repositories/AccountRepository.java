package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;

@Repository
public interface AccountRepository extends JpaRepository<AccountEnt, Long> {
    AccountEnt findByLogin(String login);
}
