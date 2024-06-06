package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrearCategoriaUseCaseImplTest {

    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CrearCategoriaUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        categoriaDomain.setUsuarioDomain(usuario);
        categoriaDomain.setId(id);
        categoriaDomain.setNombre("prueba");
        categoriaDomain.setDescripcion("prueba");
    }

    @Test
    void crearCategoria_exitoso() {
        useCase.execute(categoriaDomain);

        verify(repository, times(1)).save(any());
        assertEquals("prueba", categoriaDomain.getNombre());
    }

    @Test
    void crearCategoria_integridadDatosException() {
        doThrow(DataIntegrityViolationException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void crearCategoria_transaccionException() {
        doThrow(TransactionRequiredException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void crearCategoria_jpaException() {
        doThrow(JpaSystemException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }
}
