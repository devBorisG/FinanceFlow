package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.meta;

import finance.corp.financeflowdomain.entity.MetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MetaRepositoryAdapter extends JpaRepository<MetaEntity, UUID> {
    Optional<List<MetaEntity>> findByUsuario_Id(UUID id);
}
