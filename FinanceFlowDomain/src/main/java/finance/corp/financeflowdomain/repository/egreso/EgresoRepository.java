package finance.corp.financeflowdomain.repository.egreso;

import finance.corp.financeflowdomain.entity.EgresoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EgresoRepository  {
    Optional<List<EgresoEntity>> findByUsuarioId(UUID id);

    void save(EgresoEntity egresoEntity);
    boolean existsById(UUID id);
    void delete(EgresoEntity egresoEntity);

}
