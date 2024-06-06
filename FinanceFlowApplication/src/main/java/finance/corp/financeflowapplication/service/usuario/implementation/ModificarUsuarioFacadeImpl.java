package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.ModificarUsuarioFacade;
import finance.corp.financeflowapplication.validator.usuario.ModificarUsuarioValidator;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.ModificarUsuarioUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModificarUsuarioFacadeImpl implements ModificarUsuarioFacade {

    private final ModificarUsuarioUseCase modificarUsuarioUseCase;
    private final ModificarUsuarioValidator modificarUsuarioValidator;

    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();

    public ModificarUsuarioFacadeImpl(ModificarUsuarioUseCase modificarUsuarioUseCase, ModificarUsuarioValidator modificarUsuarioValidator) {
        this.modificarUsuarioUseCase = modificarUsuarioUseCase;
        this.modificarUsuarioValidator = modificarUsuarioValidator;
    }

    @Override
    public void execute(UsuarioDTO dto) {
        try {
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
            //modificarUsuarioValidator.isValid(dto);
            modificarUsuarioUseCase.execute(usuarioDomain);
        } catch (AplicationCustomException e) {
            throw e;
        } catch (
                TransactionException e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error en la transaccion.");
        } catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado ejecutando la transaccion para modificar los datos del usuario.");
        }
    }
}
