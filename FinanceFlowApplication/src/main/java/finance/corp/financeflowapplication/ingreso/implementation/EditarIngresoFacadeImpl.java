package finance.corp.financeflowapplication.ingreso.implementation;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.ingreso.EditarIngresoFacade;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.ingreso.EditarIngresoUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EditarIngresoFacadeImpl implements EditarIngresoFacade {

    MapperDTOToDomain<IngresoDTO, IngresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final EditarIngresoUseCase useCase;

    public EditarIngresoFacadeImpl(EditarIngresoUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(IngresoDTO dto) {
        IngresoDomain ingresoDomain = mapperDTOToDomain.mapToDomain(dto,IngresoDomain.class);
        ingresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
        try{
            useCase.execute(ingresoDomain);
        }catch(Exception exception){
            throw exception;
        }
    }
}
