package finance.corp.financeflowapplication.service.usecase.ingreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.repository.ingreso.IngresoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrearIngresoUseCaseImplTest {
    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuarioDomain = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();
    IngresoDomain ingresoDomain = new IngresoDomain();
    @Mock
    IngresoRepository repository;
    @InjectMocks
    CrearIngresoUseCaseImpl useCase;
    @BeforeEach
    void setUp(){
        usuarioDomain.setId(id);
        usuarioDomain.setNombre("prueba");
        usuarioDomain.setApellido("prueba");
        usuarioDomain.setContrasena("prueba");
        usuarioDomain.setCorreo("prueba@prueba.com");

        categoriaDomain.setUsuarioDomain(usuarioDomain);
        categoriaDomain.setId(id);
        categoriaDomain.setNombre("prueba");
        categoriaDomain.setDescripcion("prueba");

        ingresoDomain.setId(id);
        ingresoDomain.setUsuario(usuarioDomain);
        ingresoDomain.setCategoria(categoriaDomain);
        ingresoDomain.setNombre("prueba");
        ingresoDomain.setDescripcion("prueba");
        ingresoDomain.setMonto(2);
        ingresoDomain.setPeriodicidad(2);
    }

    @Test
    void crearIngresoExitoso(){
        useCase.execute(ingresoDomain);
        verify(repository, times(1)).save(any());
        assertEquals("prueba", ingresoDomain.getNombre());
    }

    @Test
    void crearIngresoIntegridadDatos(){
        doThrow(DataIntegrityViolationException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
    }

    @Test
    void crearIngresoTransaccionException(){
        doThrow(TransactionRequiredException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(ingresoDomain));
    }

    @Test
    void crearIngresoJpaException(){
        doThrow(JpaSystemException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
    }

}