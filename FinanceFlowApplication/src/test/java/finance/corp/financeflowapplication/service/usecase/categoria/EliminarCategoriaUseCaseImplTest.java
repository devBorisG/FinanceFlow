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

@ExtendWith(MockitoExtension.class)
public class EliminarCategoriaUseCaseImplTest {
    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    EliminarCategoriaUseCaseImpl useCase;

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
    void EliminarCategoria_exitoso() {
        useCase.execute(categoriaDomain);
        verify(repository, times(1)).delete(any());
        assertEquals(id, categoriaDomain.getId());
    }

    @Test
    void EliminarCategoria_integridadDatosException() {
        doThrow(DataIntegrityViolationException.class).when(repository).delete(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void EliminarCategoria_transaccionException() {
        doThrow(TransactionRequiredException.class).when(repository).delete(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }

    @Test
    void EliminarCategoria_jpaException() {
        doThrow(JpaSystemException.class).when(repository).delete(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(categoriaDomain));
    }
}
