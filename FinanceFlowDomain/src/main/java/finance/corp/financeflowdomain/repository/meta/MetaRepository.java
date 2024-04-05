package finance.corp.financeflowdomain.repository.meta;

import finance.corp.financeflowdomain.entity.MetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MetaRepository extends JpaRepository<MetaEntity, UUID> {
    MetaEntity findByNombre(String nombre);
}
