package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.port.input.usuario.ConsultarUsuarioUseCase;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EliminarUsuarioUseCaseImplTest {

    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuarioDomain;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    ConsultarUsuarioUseCase consultarUsuarioUseCase;

    @InjectMocks
    EliminarUsuarioUseCaseImpl eliminarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioDomain = new UsuarioDomain();
        usuarioDomain.setId(id);
        usuarioDomain.setNombre("prueba");
        usuarioDomain.setApellido("prueba");
        usuarioDomain.setContrasena("prueba");
        usuarioDomain.setCorreo("prueba@prueba.com");
    }

    @Test
    void eliminarUsuario_exitoso() {
        when(consultarUsuarioUseCase.execute(any())).thenReturn(List.of(usuarioDomain));
        eliminarUsuarioUseCase.execute(usuarioDomain);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void eliminarUsuario_noEncontrado() {
        when(consultarUsuarioUseCase.execute(any())).thenReturn(Collections.emptyList());
        assertThrows(AplicationCustomException.class, () -> eliminarUsuarioUseCase.execute(usuarioDomain));
        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void eliminarUsuario_jpaException() {
        when(consultarUsuarioUseCase.execute(any())).thenThrow(JpaSystemException.class);
        assertThrows(AplicationCustomException.class, () -> eliminarUsuarioUseCase.execute(usuarioDomain));
        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void eliminarUsuario_otraException() {
        when(consultarUsuarioUseCase.execute(any())).thenThrow(RuntimeException.class);
        assertThrows(AplicationCustomException.class, () -> eliminarUsuarioUseCase.execute(usuarioDomain));
        verify(usuarioRepository, never()).deleteById(any());
    }
}
