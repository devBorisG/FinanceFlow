package finance.corp.financeflowdomain.repository.egreso;

import finance.corp.financeflowdomain.entity.EgresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EgresoRepository extends JpaRepository<EgresoEntity, UUID>{
}
