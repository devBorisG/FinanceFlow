package finance.corp.financeflowapplication.service.usecase.ingreso;

import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.port.input.ingreso.EliminarIngresoUseCase;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.stereotype.Service;

@Service
public class EliminarIngresoUseCaseImpl implements EliminarIngresoUseCase {

    MapperDomainToEntity<IngresoDomain, IngresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final IngresoRepository ingresoRepository;

    public EliminarIngresoUseCaseImpl(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    @Override
    public void execute(IngresoDomain domain) {
        try{
            ingresoRepository.delete(mapperDomainToEntity.mapToEntity(domain,IngresoEntity.class));
        } catch(Exception exception){
            throw exception;
        }
    }
}
