package finance.corp.financeflowapplication.service.egreso.implementation;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.egreso.EditarEgresoFacade;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.egreso.EditarEgresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EditarEgresoFacadeImpl implements EditarEgresoFacade {
    MapperDTOToDomain<EgresoDTO, EgresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final EditarEgresoUseCase useCase;

    public EditarEgresoFacadeImpl(EditarEgresoUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(EgresoDTO dto) {
        EgresoDomain egresoDomain = mapperDTOToDomain.mapToDomain(dto,EgresoDomain.class);
        egresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
        try{
            useCase.execute(egresoDomain);
        }catch (AplicationCustomException e) {
            throw e;
        } catch (TransactionException e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error en la transaccion.");
        } catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado ejecutando la transaccion para modificar los datos del usuario.");
        }

    }
}
