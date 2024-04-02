package finance.corp.financeflowapplication.service.adapter.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.port.usuario.CrearUsuarioFacade;
import finance.corp.financeflowapplication.validator.port.usuario.CrearUsuarioValidator;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.usecase.port.usuario.CrearUsuarioUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;

@Service
@Transactional
public class CrearUsuarioFacadeImpl implements CrearUsuarioFacade {
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final CrearUsuarioUseCase useCase;
    private final CrearUsuarioValidator validator;

    public CrearUsuarioFacadeImpl(CrearUsuarioUseCase useCase, CrearUsuarioValidator validator) {
        this.useCase = useCase;
        this.validator = validator;
    }

    @Override
    public void execute(UsuarioDTO dto) {
        try{
            validator.isValid(dto);
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
            useCase.execute(usuarioDomain);
        } catch (DomainCustomException | AplicationCustomException e) {
            throw e;
        } catch (TransactionException e){
            throw DomainCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion.");
        } catch (Exception e){
            throw DomainCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion.");
        }
    }
}
