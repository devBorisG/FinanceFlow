package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.EnviarCorreoRecuperacionUseCase;
import finance.corp.financeflowdomain.port.output.email.SendEmail;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import org.springframework.stereotype.Service;

@Service
public class EnviarCorreoRecuperacionUseCaseImpl implements EnviarCorreoRecuperacionUseCase {

    private final SendEmail sendEmail;
    private final TokenRepository tokenRepository;
    private final UsuarioRepository usuarioRepository;

    public EnviarCorreoRecuperacionUseCaseImpl(SendEmail sendEmail, TokenRepository tokenRepository, UsuarioRepository usuarioRepository) {
        this.sendEmail = sendEmail;
        this.tokenRepository = tokenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void execute(TokenDomain domain) {
        UsuarioEntity usuarioEntity;
        try {
            usuarioEntity = usuarioRepository.findByCorreo(domain
                    .getUsuarioDomain()
                    .getCorreo());
//            domain.setUsuarioDomain();
//            tokenRepository.save()
//            sendEmail.sendEmail(domain.getCorreo());
        }catch (InfraestructureCustomException e){
            throw DomainCustomException.createTechnicalException(e, e.getMessage());
        }
    }
}
