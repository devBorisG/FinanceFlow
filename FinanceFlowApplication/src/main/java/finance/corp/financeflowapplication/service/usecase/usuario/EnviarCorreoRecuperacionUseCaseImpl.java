package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.EnviarCorreoRecuperacionUseCase;
import finance.corp.financeflowdomain.port.output.email.SendEmail;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnviarCorreoRecuperacionUseCaseImpl implements EnviarCorreoRecuperacionUseCase {
    MapperDomainToEntity<TokenDomain, TokenEntity> mapperDomainToEntity = new MapperDomainToEntity<>();
    MapperDomainToEntity<UsuarioDomain,UsuarioEntity> mapperDomainToEntityUsuario = new MapperDomainToEntity<>();
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
        TokenEntity tokenEntity = mapperDomainToEntity.mapToEntity(domain, TokenEntity.class);
        tokenEntity.setUsuario(mapperDomainToEntityUsuario.mapToEntity(domain.getUsuarioDomain(), UsuarioEntity.class));
        try {
            Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByCorreo(tokenEntity
                    .getUsuario()
                    .getCorreo()
            );
            if(usuarioEntity.isPresent()){
                tokenEntity.setUsuario(usuarioEntity.get());
                tokenRepository.save(tokenEntity);
                sendEmail.send(domain.getUsuarioDomain().getCorreo(), domain.getToken());
            }
        }catch (InfraestructureCustomException e){
            throw DomainCustomException.createTechnicalException(e, e.getMessage());
        }
    }
}
