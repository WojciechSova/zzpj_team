package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.AccountEnt;
import pl.zzpj.entities.TransactionEnt;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEnt, Long> {
    List<TransactionEnt> findAllByFromIdOrToId(AccountEnt fromId, AccountEnt toId);
}
