package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CrearUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CrearUsuarioUseCaseImpl crearUsuarioUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        crearUsuarioUseCase = new CrearUsuarioUseCaseImpl(usuarioRepository, passwordEncoder);
    }

    @Test
    public void testExecute() {
        UsuarioDomain usuarioDomain = new UsuarioDomain();
        usuarioDomain.setContrasena("contraseña");
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");
        crearUsuarioUseCase.execute(usuarioDomain);
        verify(passwordEncoder, times(1)).encode(any());
        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    public void testExecute_DataIntegrityViolationException() {
        UsuarioDomain usuarioDomain = new UsuarioDomain();
        usuarioDomain.setContrasena("contraseña");
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");
        doThrow(DataIntegrityViolationException.class).when(usuarioRepository).save(any());
        assertThrows(DomainCustomException.class, () -> crearUsuarioUseCase.execute(usuarioDomain));
    }
}



