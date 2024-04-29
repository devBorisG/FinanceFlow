package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.ingreso.implementation;

import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowinfrastructure.adapter.secondary.jpa.ingreso.IngresoRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaIngresoRepositoryImpl implements IngresoRepository {

    private final IngresoRepositoryAdapter ingresoRepositoryAdapter;

    public JpaIngresoRepositoryImpl(IngresoRepositoryAdapter ingresoRepositoryAdapter) {
        this.ingresoRepositoryAdapter = ingresoRepositoryAdapter;
    }

    @Override
    public Optional<List<IngresoEntity>> findByUsuarioId(UUID id) {
        return ingresoRepositoryAdapter.findByUsuario_Id(id);
    }

    @Override
    public void save(IngresoEntity ingresoEntity) {
        ingresoRepositoryAdapter.save(ingresoEntity);
    }

    @Override
    public boolean existsById(UUID id) {
        return ingresoRepositoryAdapter.existsById(id);
    }

    @Override
    public void delete(IngresoEntity ingresoEntity) {
        ingresoRepositoryAdapter.delete(ingresoEntity);
    }
}
