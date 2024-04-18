package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.validator.usuario.CorreoValidator;
import finance.corp.financeflowapplication.validator.usuario.EnviarCorreoValidator;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnviarCorreoValidatorImpl implements EnviarCorreoValidator {
    private final TokenRepository tokenRepository;
    public EnviarCorreoValidatorImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void isValid(TokenDTO dto) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByUsuario_Id(dto.getToken());
        tokenEntity.ifPresent(tokenRepository::delete);
    }
}
