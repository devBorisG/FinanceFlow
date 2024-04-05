package finance.corp.financeflowdomain.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.EnviarCorreoRecuperacionUseCase;
import finance.corp.financeflowdomain.port.output.email.SendEmail;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import org.springframework.stereotype.Service;

@Service
public class EnviarCorreoRecuperacionUseCaseImpl implements EnviarCorreoRecuperacionUseCase {

    private final SendEmail sendEmail;

    public EnviarCorreoRecuperacionUseCaseImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void execute(UsuarioDomain domain) {
        try {
            sendEmail.sendEmail(domain.getCorreo());
        }catch (InfraestructureCustomException e){
            throw DomainCustomException.createTechnicalException(e, e.getMessage());
        }
    }
}
