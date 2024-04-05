package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.EnviarCorreoRecuperacionFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.EnviarCorreoRecuperacionUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;

@Service
public class EnviarCorreoRecuperacionFacadeImpl implements EnviarCorreoRecuperacionFacade {

    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final EnviarCorreoRecuperacionUseCase useCase;

    public EnviarCorreoRecuperacionFacadeImpl(EnviarCorreoRecuperacionUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(UsuarioDTO dto) {
        try {
            UsuarioDomain usuarioDomain = mapperDTOToDomain.mapToDomain(dto, UsuarioDomain.class);
            useCase.execute(usuarioDomain);
        } catch (AplicationCustomException e) {
            throw e;
        } catch (DomainCustomException e) {
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        } catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado");
        }
    }
}
