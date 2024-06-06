package finance.corp.financeflowapplication.service.usecase.ingreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.entity.IngresoEntity;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import finance.corp.financeflowutils.mapper.MapperEntityToDomain;
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
class ConsultarIngresoUseCaseImplTest {

    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoria = new CategoriaDomain();
    IngresoDomain ingresoDomain = new IngresoDomain();

    @Mock
    IngresoRepository repository;

    @InjectMocks
    ConsultarIngresoUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        categoria.setId(id);
        categoria.setNombre("prueba");

        ingresoDomain.setId(id);
        ingresoDomain.setUsuario(usuario);
        ingresoDomain.setCategoria(categoria);
        ingresoDomain.setDescripcion("prueba");
    }

    @Test
    void consultarIngresos_exitoso() {
        when(repository.findByUsuarioId(any())).thenReturn(Optional.of(Collections.singletonList(new IngresoEntity())));
        List<IngresoDomain> result = useCase.execute(Optional.of(ingresoDomain));
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void consultarIngresos_sinResultados() {
        when(repository.findByUsuarioId(any())).thenReturn(Optional.empty());
        List<IngresoDomain> result = useCase.execute(Optional.of(ingresoDomain));
        assertTrue(result.isEmpty());
    }

    @Test
    void consultarIngresos_dataIntegrityException() {
        when(repository.findByUsuarioId(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(ingresoDomain)));
    }

    @Test
    void consultarIngresos_transactionException() {
        when(repository.findByUsuarioId(any())).thenThrow(TransactionRequiredException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(ingresoDomain)));
    }

    @Test
    void consultarIngresos_jpaException() {
        when(repository.findByUsuarioId(any())).thenThrow(JpaSystemException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(ingresoDomain)));
    }
}
