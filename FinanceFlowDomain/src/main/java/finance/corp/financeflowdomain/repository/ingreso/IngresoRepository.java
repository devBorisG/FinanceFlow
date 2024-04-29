package finance.corp.financeflowdomain.repository.ingreso;

import finance.corp.financeflowdomain.entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngresoRepository{

    Optional<List<IngresoEntity>> findByUsuarioId(UUID id);
    void save(IngresoEntity ingresoEntity);
    boolean existsById(UUID id);
    void delete(IngresoEntity ingresoEntity);
}