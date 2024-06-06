package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.egreso.implementation;

import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowinfrastructure.adapter.secondary.jpa.egreso.EgresoRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaEgresoRepositoryImpl implements EgresoRepository {

    private final EgresoRepositoryAdapter egresoRepositoryAdapter;

    public JpaEgresoRepositoryImpl(EgresoRepositoryAdapter egresoRepositoryAdapter) {
        this.egresoRepositoryAdapter = egresoRepositoryAdapter;
    }

    @Override
    public Optional<List<EgresoEntity>> findByUsuarioId(UUID id) {
        return egresoRepositoryAdapter.findByUsuario_Id(id);
    }

    @Override
    public void save(EgresoEntity egresoEntity) {
        egresoRepositoryAdapter.save(egresoEntity);
    }

    @Override
    public boolean existsById(UUID id) {
        return egresoRepositoryAdapter.existsById(id);
    }

    @Override
    public void delete(EgresoEntity egresoEntity) {
        egresoRepositoryAdapter.deleteById(egresoEntity.getId());
    }
}
