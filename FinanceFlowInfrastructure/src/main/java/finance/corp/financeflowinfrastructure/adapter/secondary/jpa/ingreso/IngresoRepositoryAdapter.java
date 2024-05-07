package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.ingreso;

import finance.corp.financeflowdomain.entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngresoRepositoryAdapter extends JpaRepository<IngresoEntity, UUID> {
    Optional<List<IngresoEntity>> findByUsuario_Id(UUID id);
}
