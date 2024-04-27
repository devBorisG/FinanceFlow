package finance.corp.financeflowdomain.repository.egreso;

import finance.corp.financeflowdomain.entity.EgresoEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Configuration
@Repository
public interface EgresoRepository  {
    Optional<List<EgresoEntity>> findByUsuario(UUID id);
    void save(EgresoEntity  egresoEntity);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
