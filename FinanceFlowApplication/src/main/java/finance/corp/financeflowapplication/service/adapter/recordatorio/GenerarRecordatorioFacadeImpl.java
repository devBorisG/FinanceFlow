package finance.corp.financeflowapplication.service.adapter.recordatorio;

import finance.corp.financeflowapplication.dto.recordatorio.RecordatorioDTO;
import finance.corp.financeflowapplication.service.port.recordatorio.GenerarRecordatorioFacade;
import finance.corp.financeflowdomain.domain.RecordatorioDomain;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenerarRecordatorioFacadeImpl implements GenerarRecordatorioFacade {
    @Override
    public void execute(RecordatorioDTO dto) {
        try {
            //TODO: Generar recordatorio
            MapperDTOToDomain<RecordatorioDTO, RecordatorioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
            RecordatorioDomain recordatorioDomain = mapperDTOToDomain.mapToDomain(dto, RecordatorioDomain.class);
        }catch (Exception e){
            //TODO: Log error
            throw e;
        }
    }
}
