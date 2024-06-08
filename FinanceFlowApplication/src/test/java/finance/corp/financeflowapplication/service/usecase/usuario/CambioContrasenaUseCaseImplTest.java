package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.TokenDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.TokenEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.token.TokenRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import jakarta.persistence.TransactionRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CambioContrasenaUseCaseImplTest {

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CambioContrasenaUseCaseImpl useCase;

    private TokenDomain tokenDomain;
    private TokenEntity tokenEntity;
    private UsuarioEntity usuarioEntity;
    private UUID tokenUUID;

    @BeforeEach
    void setUp() {
        tokenUUID = UUID.randomUUID();

        UsuarioDomain usuarioDomain = new UsuarioDomain();
        usuarioDomain.setContrasena("nuevaContrasena");

        tokenDomain = new TokenDomain();
        tokenDomain.setToken(tokenUUID);
        tokenDomain.setUsuarioDomain(usuarioDomain);

        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setContrasena("viejaContrasena");

        tokenEntity = new TokenEntity();
        tokenEntity.setToken(tokenUUID);
        tokenEntity.setUsuario(usuarioEntity);
        tokenEntity.setFechaExpiracion(LocalDateTime.now().plusMinutes(10));
    }

    @Test
    void execute_tokenValido_cambioContrasena() {
        when(tokenRepository.findById(tokenUUID)).thenReturn(Optional.of(tokenEntity));
        when(passwordEncoder.encode("nuevaContrasena")).thenReturn("contrasenaEncriptada");
        useCase.execute(tokenDomain);
        assertEquals("contrasenaEncriptada", usuarioEntity.getContrasena());
        verify(tokenRepository).save(tokenEntity);
        verify(tokenRepository).delete(tokenEntity);
    }

    @Test
    void execute_tokenExpirado_noCambioContrasena() {
        tokenEntity.setFechaExpiracion(LocalDateTime.now().minusMinutes(1));
        when(tokenRepository.findById(tokenUUID)).thenReturn(Optional.of(tokenEntity));
        useCase.execute(tokenDomain);
        assertEquals("viejaContrasena", usuarioEntity.getContrasena());
        verify(tokenRepository).delete(tokenEntity);
    }

    @Test
    void execute_tokenNoEncontrado() {
        when(tokenRepository.findById(tokenUUID)).thenReturn(Optional.empty());
        useCase.execute(tokenDomain);
        assertEquals("viejaContrasena", usuarioEntity.getContrasena());
        verify(tokenRepository, never()).save(any());
        verify(tokenRepository, never()).delete(any());
    }

    @Test
    void execute_dataAccessException() {
        when(tokenRepository.findById(tokenUUID)).thenThrow(new DataAccessException("Error") {});
        AplicationCustomException exception = assertThrows(AplicationCustomException.class, () -> useCase.execute(tokenDomain));
        assertEquals("Error al intentar recuperar el id del token.", exception.getMessage());
    }

    @Test
    void execute_transactionRequiredException() {
        when(tokenRepository.findById(tokenUUID)).thenThrow(TransactionRequiredException.class);
        AplicationCustomException exception = assertThrows(AplicationCustomException.class, () -> useCase.execute(tokenDomain));
        assertEquals("Se requiere una transacción para esta operación.", exception.getMessage());
    }
}
