package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.CrearUsuarioFacade;
import finance.corp.financeflowapplication.validator.usuario.CrearUsuarioValidator;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.CrearUsuarioUseCase;
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
            System.out.println("usuarioDomain = " + usuarioDomain);
        } catch (AplicationCustomException e) {
            throw e;
        }   catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }
        catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion.");
        } catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion.");
        }
    }
}
