package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.port.input.egreso.EliminarEgresoUseCase;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.stereotype.Service;

@Service
public class EliminarEgresoUseCaseImpl implements EliminarEgresoUseCase {

    MapperDomainToEntity<EgresoDomain, EgresoEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final EgresoRepository repository;

    public EliminarEgresoUseCaseImpl(EgresoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(EgresoDomain domain) {
        try{
            EgresoEntity egresoEntity = mapperDomainToEntity.mapToEntity(domain, EgresoEntity.class);
            System.out.println("egresoEntity = " + egresoEntity);
            repository.delete(egresoEntity);
        } catch(Exception exception){
            throw exception;
        }

    }
}
