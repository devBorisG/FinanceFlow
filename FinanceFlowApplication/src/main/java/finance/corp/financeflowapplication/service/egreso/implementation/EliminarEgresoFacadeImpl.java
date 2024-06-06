package finance.corp.financeflowapplication.service.egreso.implementation;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.service.egreso.EliminarEgresoFacade;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.port.input.egreso.EliminarEgresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EliminarEgresoFacadeImpl implements EliminarEgresoFacade {
    private final EliminarEgresoUseCase eliminarEgresoUseCase;
    MapperDTOToDomain<EgresoDTO, EgresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();

    public EliminarEgresoFacadeImpl(EliminarEgresoUseCase eliminarEgresoUseCase) {
        this.eliminarEgresoUseCase = eliminarEgresoUseCase;
    }

    @Override
    public void execute(EgresoDTO dto) {
        try{
            eliminarEgresoUseCase.execute(mapperDTOToDomain.mapToDomain(dto, EgresoDomain.class));
        }catch (AplicationCustomException aplicationCustomException){
            throw aplicationCustomException;
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e, "ocurrio un error eliminando el egreso"+ e.getMessage());

        }
    }
}
