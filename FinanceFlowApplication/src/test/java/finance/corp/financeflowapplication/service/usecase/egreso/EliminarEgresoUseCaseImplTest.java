package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
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

public class EliminarEgresoUseCaseImplTest {
    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();
    EgresoDomain egresoDomain = new EgresoDomain();

    @Mock
    private EgresoRepository egresoRepository;

    @InjectMocks
    private EliminarEgresoUseCaseImpl useCase;

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

        egresoDomain.setCategoria(categoriaDomain);
        egresoDomain.setUsuario(usuario);
        egresoDomain.setId(id);
        egresoDomain.setNombre("prueba");
        egresoDomain.setDescripcion("prueba");
        egresoDomain.setMonto(12323);
        egresoDomain.setPeriodicidad(1);
    }

    @Test
    void EliminarEgreso_exitoso() {
        useCase.execute(egresoDomain);
        verify(egresoRepository, times(1)).delete(any());
        assertEquals(id, egresoDomain.getId());
    }

    @Test
    void EliminarEgresoIntegridadDatos(){
        doThrow(DataIntegrityViolationException.class).when(egresoRepository).delete(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(egresoDomain));
    }

    @Test
    void EliminarEgresoTransaccionException(){
        doThrow(TransactionRequiredException.class).when(egresoRepository).delete(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(egresoDomain));
    }

    @Test
    void EliminarEgresoJpaException(){
        doThrow(JpaSystemException.class).when(egresoRepository).delete(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(egresoDomain));
    }
}
