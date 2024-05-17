package finance.corp.financeflowinfrastructure.adapter.secondary.egreso;

import finance.corp.financeflowdomain.entity.EgresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EgresoRepositoryAdapter extends JpaRepository<EgresoEntity, UUID> {
    Optional<List<EgresoEntity>> findByUsuario_Id(UUID id);
}
