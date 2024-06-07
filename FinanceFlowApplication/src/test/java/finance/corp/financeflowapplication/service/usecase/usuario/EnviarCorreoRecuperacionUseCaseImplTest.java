package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.output.email.SendEmail;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.exception.infraestructure.InfraestructureCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnviarCorreoRecuperacionUseCaseImplTest {

    @Mock
    SendEmail sendEmail;

    @Mock
    TokenRepository tokenRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    EnviarCorreoRecuperacionUseCaseImpl useCase;

    TokenDomain tokenDomain;
    TokenEntity tokenEntity;
    UsuarioDomain usuarioDomain;
    UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        usuarioDomain = new UsuarioDomain();
        usuarioDomain.setCorreo("test@example.com");

        tokenDomain = new TokenDomain();
        tokenDomain.setUsuarioDomain(usuarioDomain);
        tokenDomain.setToken(UUID.randomUUID());

        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setCorreo("test@example.com");

        tokenEntity = new TokenEntity();
        tokenEntity.setUsuario(usuarioEntity);
        tokenEntity.setToken(tokenDomain.getToken());
    }

    @Test
    void execute_usuarioEncontrado() {
        when(usuarioRepository.findByCorreo(usuarioEntity.getCorreo())).thenReturn(Optional.of(usuarioEntity));

        useCase.execute(tokenDomain);

        verify(tokenRepository, times(1)).save(any(TokenEntity.class));
        verify(sendEmail, times(1)).send(tokenDomain.getUsuarioDomain().getCorreo(), tokenDomain.getToken());
    }

    @Test
    void execute_usuarioNoEncontrado() {
        when(usuarioRepository.findByCorreo(usuarioEntity.getCorreo())).thenReturn(Optional.empty());

        useCase.execute(tokenDomain);

        verify(tokenRepository, never()).save(any(TokenEntity.class));
        verify(sendEmail, never()).send(eq(tokenDomain.getUsuarioDomain().getCorreo()), eq(tokenDomain.getToken()));
    }
}
