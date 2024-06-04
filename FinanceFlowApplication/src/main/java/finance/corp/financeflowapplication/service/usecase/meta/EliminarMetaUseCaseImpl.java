package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.port.input.meta.EliminarMetaUseCase;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;


@Service
public class EliminarMetaUseCaseImpl implements EliminarMetaUseCase {
    MapperDomainToEntity<MetaDomain, MetaEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    private final MetaRepository metaRepository;

    public EliminarMetaUseCaseImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public void execute(MetaDomain domain) {
        try{
            metaRepository.delete(mapperDomainToEntity.mapToEntity(domain,MetaEntity.class));
        }catch (Exception exception) {
            throw AplicationCustomException.createTechnicalException("errror no se logro eliminar ");
        }
    }
}

