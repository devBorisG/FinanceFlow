package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.meta.EliminarMetaFacade;
import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.port.input.meta.EliminarMetaUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EliminarMetaFacadeImpl implements EliminarMetaFacade {
    private final EliminarMetaUseCase eliminarMetaUseCase;
    MapperDTOToDomain<MetaDTO, MetaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();

    public EliminarMetaFacadeImpl(EliminarMetaUseCase eliminarMetaUseCase) {
        this.eliminarMetaUseCase = eliminarMetaUseCase;
    }

    @Override
    public void execute(MetaDTO dto) {
        try{
            eliminarMetaUseCase.execute(mapperDTOToDomain.mapToDomain(dto, MetaDomain.class));
        }catch (Exception e){
            throw e;
        }
    }
}
