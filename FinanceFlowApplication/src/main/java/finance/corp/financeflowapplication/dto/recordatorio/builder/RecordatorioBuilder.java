package finance.corp.financeflowapplication.dto.recordatorio.builder;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public interface RecordatorioBuilder {
    RecordatorioDTOBuilder setId(UUID id);
    RecordatorioDTOBuilder setFecha(LocalDateTime fecha);
    RecordatorioDTOBuilder setPeriodicidad(int periodicidad);
    RecordatorioDTOBuilder setMeta(MetaDTO meta);
}
