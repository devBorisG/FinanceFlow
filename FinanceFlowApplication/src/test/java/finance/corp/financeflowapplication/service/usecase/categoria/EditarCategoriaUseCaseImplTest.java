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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
public class EditarCategoriaUseCaseImplTest {

    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();
    @Mock
    CategoriaRepository repository;

    @InjectMocks
    EditarCategoriaUseCaseImpl useCase;

    @BeforeEach
    void setUp() {

        usuario.setId(null);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        categoriaDomain.setUsuarioDomain(usuario);
        categoriaDomain.setId(null);
        categoriaDomain.setNombre("prueba");
        categoriaDomain.setDescripcion("prueba");
    }

    @Test
    void EditarCategoria_exitoso() {
        useCase.execute(categoriaDomain);

        verify(repository, times(1)).save(any());
        assertEquals("prueba", categoriaDomain.getNombre());
    }

    @Test
    void EditarCategoria_integridadDatosException() {
        doThrow(DataIntegrityViolationException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void EditarCategoria_transaccionException() {
        doThrow(TransactionRequiredException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void EditarCategoria_jpaException() {
        doThrow(JpaSystemException.class).when(repository).save(any());

        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }
}
