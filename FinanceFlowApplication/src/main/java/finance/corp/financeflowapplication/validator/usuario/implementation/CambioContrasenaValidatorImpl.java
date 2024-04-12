package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.validator.usuario.CambioContrasenaValidator;
import finance.corp.financeflowapplication.validator.usuario.ContrasenaValidator;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
public class CambioContrasenaValidatorImpl implements CambioContrasenaValidator {
    private final ContrasenaValidator contrasenaValidator;
    private final TokenRepository tokenRepository;

    public CambioContrasenaValidatorImpl(ContrasenaValidator contrasenaValidator, TokenRepository tokenRepository) {
        this.contrasenaValidator = contrasenaValidator;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void isValid(TokenDTO dto) {
        LocalDateTime fechaActual = LocalDateTime.now();
        contrasenaValidator.isValid(dto.getUsuarioDTO().getContrasena());
        Optional<TokenEntity> tokenEntity = tokenRepository.findById(dto.getToken());
        if(tokenEntity.isPresent() && fechaActual.isAfter(tokenEntity.get().getFechaExpiracion())){
            tokenRepository.delete(tokenEntity.get());
            throw AplicationCustomException.createUserException("El tiempo valido para cambiar la contraseña ha expirado.");
        }
    }
}
