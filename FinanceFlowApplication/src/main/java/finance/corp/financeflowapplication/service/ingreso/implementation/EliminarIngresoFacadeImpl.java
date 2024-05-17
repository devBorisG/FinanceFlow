package finance.corp.financeflowapplication.service.ingreso.implementation;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.service.ingreso.EliminarIngresoFacade;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.port.input.ingreso.EliminarIngresoUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EliminarIngresoFacadeImpl implements EliminarIngresoFacade {

    MapperDTOToDomain<IngresoDTO, IngresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final EliminarIngresoUseCase useCase;

    public EliminarIngresoFacadeImpl(EliminarIngresoUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(IngresoDTO dto) {
        try{
            useCase.execute(mapperDTOToDomain.mapToDomain(dto,IngresoDomain.class));
        }catch (Exception exception){
            throw exception;
        }
    }
}
