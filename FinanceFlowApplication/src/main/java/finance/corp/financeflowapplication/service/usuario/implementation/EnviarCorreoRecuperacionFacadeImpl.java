package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.EnviarCorreoRecuperacionFacade;
import finance.corp.financeflowapplication.validator.usuario.EnviarCorreoValidator;
import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.EnviarCorreoRecuperacionUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import org.springframework.stereotype.Service;

@Service
public class EnviarCorreoRecuperacionFacadeImpl implements EnviarCorreoRecuperacionFacade {

    MapperDTOToDomain<TokenDTO, TokenDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    private final EnviarCorreoRecuperacionUseCase useCase;
    private final EnviarCorreoValidator validator;
    public EnviarCorreoRecuperacionFacadeImpl(EnviarCorreoRecuperacionUseCase useCase, EnviarCorreoValidator validator) {
        this.useCase = useCase;
        this.validator = validator;
    }

    @Override
    public void execute(TokenDTO dto) {
        try {
            validator.isValid(dto);
            TokenDomain tokenDomain = mapperDTOToDomain.mapToDomain(dto, TokenDomain.class);
            tokenDomain.setUsuarioDomain(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuarioDTO(), UsuarioDomain.class));
            useCase.execute(tokenDomain);
        } catch (AplicationCustomException e) {
            throw e;
        } catch (DomainCustomException e) {
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        } catch (Exception e) {
            throw AplicationCustomException.createTechnicalException(e, "Ocurrio un error inesperado");
        }
    }
}
