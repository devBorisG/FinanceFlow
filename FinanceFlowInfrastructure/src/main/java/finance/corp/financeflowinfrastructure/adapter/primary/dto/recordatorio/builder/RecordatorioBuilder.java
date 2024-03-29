package finance.corp.financeflowinfrastructure.adapter.primary.dto.recordatorio.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.meta.MetaDTO;

import java.util.Date;
import java.util.UUID;

public interface RecordatorioBuilder {
    RecordatorioDTOBuilder setId(UUID id);
    RecordatorioDTOBuilder setFecha(Date fecha);
    RecordatorioDTOBuilder setPeriodicidad(int periodicidad);
    RecordatorioDTOBuilder setMeta(MetaDTO meta);
}
