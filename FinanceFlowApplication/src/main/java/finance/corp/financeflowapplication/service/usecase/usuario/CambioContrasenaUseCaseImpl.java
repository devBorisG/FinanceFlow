package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.port.input.usuario.CambioContrasenaUseCase;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CambioContrasenaUseCaseImpl implements CambioContrasenaUseCase{
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public CambioContrasenaUseCaseImpl(TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(TokenDomain domain) {
        try{
            //TODO: Validar que la fecha de caducidad no se haya vencido
            //TODO: Eliminar el token al finalizar la transaccion
            Optional<TokenEntity> tokenEntity = tokenRepository.findById(domain.getToken());

            if (tokenEntity.isPresent()){
                tokenEntity.get().getUsuario().setContrasena(
                        passwordEncoder.encode(
                                domain.getUsuarioDomain().getContrasena()
                        )
                );
                tokenRepository.save(tokenEntity.get());
            }
        }catch (DataAccessException e){
            throw AplicationCustomException.createTechnicalException(e, "Error al intentar recuperar el id del token.");
        }catch (NoSuchElementException e){
            throw AplicationCustomException.createTechnicalException(e, "No se encontro el token.");
        }catch (TransactionRequiredException e){
            throw AplicationCustomException.createTechnicalException(e, "Se requiere una transacción para esta operación.");
        }
    }
}
