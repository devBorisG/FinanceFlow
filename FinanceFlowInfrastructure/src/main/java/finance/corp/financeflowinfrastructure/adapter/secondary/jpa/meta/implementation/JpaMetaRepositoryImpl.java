package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.meta.implementation;

import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowinfrastructure.adapter.secondary.jpa.meta.MetaRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaMetaRepositoryImpl implements MetaRepository {

    private final MetaRepositoryAdapter metaRepositoryAdapter;

    public JpaMetaRepositoryImpl(MetaRepositoryAdapter metaRepositoryAdapter) {
        this.metaRepositoryAdapter = metaRepositoryAdapter;
    }

    @Override
    public Optional<List<MetaEntity>> findByUsuarioId(UUID id) {
        return metaRepositoryAdapter.findByUsuario_Id(id);
    }

    @Override
    public void save(MetaEntity metaEntity) {
        metaRepositoryAdapter.save(metaEntity);
    }

    @Override
    public boolean existsById(UUID id) {
        return metaRepositoryAdapter.existsById(id);
    }

    @Override
    public void delete(MetaEntity metaEntity) {
        metaRepositoryAdapter.delete(metaEntity);
    }
}
