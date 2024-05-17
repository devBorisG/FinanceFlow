package finance.corp.financeflowdomain.repository.meta;

import finance.corp.financeflowdomain.entity.MetaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MetaRepository{
    Optional<List<MetaEntity>> findByUsuarioId(UUID id);
    void save(MetaEntity metaEntity);
    boolean existsById(UUID id);
    void delete(MetaEntity metaEntity);
}
