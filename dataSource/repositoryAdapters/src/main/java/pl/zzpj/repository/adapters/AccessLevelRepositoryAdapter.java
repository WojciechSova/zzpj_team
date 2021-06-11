package pl.zzpj.repository.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zzpj.infrastructure.AccessLevelCRUDPort;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.repositories.AccessLevelRepository;
import pl.zzpj.repository.mappers.AccessLevelMapper;

@Repository
public class AccessLevelRepositoryAdapter implements AccessLevelCRUDPort {

    private AccessLevelRepository accessLevelRepository;

    @Autowired
    public AccessLevelRepositoryAdapter(AccessLevelRepository accessLevelRepository) {
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public AccessLevel findByLevel(String level) {
        return AccessLevelMapper.mapToAccessLevel(accessLevelRepository.findByLevel(level));
    }
}
