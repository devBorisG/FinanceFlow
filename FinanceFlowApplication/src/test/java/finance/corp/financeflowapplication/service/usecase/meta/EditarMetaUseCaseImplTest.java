package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import jakarta.persistence.TransactionRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EditarMetaUseCaseImplTest {

    UsuarioDomain usuario = new UsuarioDomain();
    MetaDomain metaDomain = new MetaDomain();

    @Mock
    MetaRepository metaRepositoryMock;

    @InjectMocks
    CrearMetaUseCaseImpl crearMetaUseCase;

    @BeforeEach
    void setUp() {
        usuario.setId(null);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        metaDomain.setUsuario(usuario);
        metaDomain.setId(null);
        metaDomain.setNombre("prueba");
        metaDomain.setDescripcion("prueba");
    }
    @Test
    void crearMeta_exitoso() {
        crearMetaUseCase.execute(metaDomain);

        verify(metaRepositoryMock, times(1)).save(any());
        assertEquals("prueba", metaDomain.getNombre());
    }
    @Test
    void crearCategoria_integridadDatosException() {
        doThrow(DataIntegrityViolationException.class).when(metaRepositoryMock).save(any());

        assertThrows(AplicationCustomException.class, () -> crearMetaUseCase.execute(metaDomain));
    }

    @Test
    void crearCategoria_transaccionException() {
        doThrow(TransactionRequiredException.class).when(metaRepositoryMock).save(any());

        assertThrows(AplicationCustomException.class, () -> crearMetaUseCase.execute(metaDomain));
    }

    @Test
    void crearCategoria_jpaException() {
        doThrow(JpaSystemException.class).when(metaRepositoryMock).save(any());

        assertThrows(AplicationCustomException.class, () -> crearMetaUseCase.execute(metaDomain));
    }
}
