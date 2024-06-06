package finance.corp.financeflowapplication.service.usecase.categoria;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarCategoriaUseCaseImplTest {

    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    ConsultarCategoriaUseCaseImpl useCase;

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
    void consultarCategorias_exitoso() {
        when(repository.findByUsuarioId(any())).thenReturn(Optional.of(Collections.singletonList(new CategoriaEntity())));
        List<CategoriaDomain> result = useCase.execute(Optional.of(categoriaDomain));
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void consultarCategorias_sinResultados() {
        when(repository.findByUsuarioId(any())).thenReturn(Optional.empty());
        List<CategoriaDomain> result = useCase.execute(Optional.of(categoriaDomain));
        assertTrue(result.isEmpty());
    }

    @Test
    void consultarCategorias_dataIntegrityException() {
        when(repository.findByUsuarioId(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(categoriaDomain)));
    }

    @Test
    void consultarCategorias_transactionException() {
        when(repository.findByUsuarioId(any())).thenThrow(TransactionRequiredException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(categoriaDomain)));
    }

    @Test
    void consultarCategorias_jpaException() {
        when(repository.findByUsuarioId(any())).thenThrow(JpaSystemException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(categoriaDomain)));
    }
}
