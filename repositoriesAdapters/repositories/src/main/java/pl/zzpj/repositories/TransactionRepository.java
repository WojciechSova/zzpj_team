package pl.zzpj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zzpj.modelEnt.TransactionEnt;

public interface TransactionRepository extends JpaRepository<TransactionEnt, Long> {
}
