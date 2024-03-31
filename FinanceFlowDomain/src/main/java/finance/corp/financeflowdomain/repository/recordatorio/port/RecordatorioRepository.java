package finance.corp.financeflowdomain.repository.recordatorio.port;

import finance.corp.financeflowdomain.entity.RecordatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordatorioRepository extends JpaRepository<RecordatorioEntity, UUID> {
}
