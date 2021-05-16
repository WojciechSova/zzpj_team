package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpj.entities.TransactionEnt;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEnt, Long> {
}
