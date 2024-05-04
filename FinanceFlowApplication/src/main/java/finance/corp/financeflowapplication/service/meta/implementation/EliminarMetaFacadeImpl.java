package finance.corp.financeflowapplication.service.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.meta.EliminarMetaFacade;
import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.port.input.meta.EliminarMetaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
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
            MetaDomain metaDomain = mapperDTOToDomain.mapToDomain(dto, MetaDomain.class);
            eliminarMetaUseCase.execute(metaDomain);
        }catch (AplicationCustomException aplicationCustomException){
            throw aplicationCustomException;
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "ocurrio un error eliminando la meta"+ e.getMessage());

        }
    }
}
