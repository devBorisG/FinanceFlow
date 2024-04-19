package finance.corp.financeflowdomain.repository.ingreso;

import finance.corp.financeflowdomain.entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngresoRepository extends JpaRepository<IngresoEntity, UUID>{
}