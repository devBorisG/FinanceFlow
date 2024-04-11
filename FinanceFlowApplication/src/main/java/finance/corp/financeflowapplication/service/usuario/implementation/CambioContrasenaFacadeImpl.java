package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.CambioContrasenaFacade;
import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.CambioContrasenaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CambioContrasenaFacadeImpl implements CambioContrasenaFacade {
    MapperDTOToDomain<TokenDTO, TokenDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> usuarioMapperDToToDomain = new MapperDTOToDomain<>();
    private final CambioContrasenaUseCase useCase;

    public CambioContrasenaFacadeImpl(CambioContrasenaUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(TokenDTO dto) {
        try {
            //TODO: Hacer validator para contrasena que si cumpla
            TokenDomain tokenDomain = mapperDTOToDomain.mapToDomain(dto, TokenDomain.class);
            tokenDomain.setUsuarioDomain(usuarioMapperDToToDomain.mapToDomain(dto.getUsuarioDTO(), UsuarioDomain.class));
            useCase.execute(tokenDomain);
        }catch (AplicationCustomException e){
            throw e;
        }catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion.");
        }catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion.");
        }
    }
}
