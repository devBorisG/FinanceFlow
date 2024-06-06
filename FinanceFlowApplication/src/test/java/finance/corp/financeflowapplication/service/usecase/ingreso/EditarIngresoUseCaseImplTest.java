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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EditarIngresoUseCaseImplTest {
    UsuarioDomain usuarioDomain = new UsuarioDomain();
    CategoriaDomain categoriaDomain = new CategoriaDomain();
    IngresoDomain ingresoDomain = new IngresoDomain();
    @Mock
    IngresoRepository repository;
    @InjectMocks
    EditarIngresoUseCaseImpl useCase;
    @BeforeEach
    void setUp(){
        usuarioDomain.setId(null);
        usuarioDomain.setNombre("prueba");
        usuarioDomain.setApellido("prueba");
        usuarioDomain.setContrasena("prueba");
        usuarioDomain.setCorreo("prueba@prueba.com");

        categoriaDomain.setUsuarioDomain(usuarioDomain);
        categoriaDomain.setId(null);
        categoriaDomain.setNombre("prueba");
        categoriaDomain.setDescripcion("prueba");

        ingresoDomain.setId(null);
        ingresoDomain.setUsuario(usuarioDomain);
        ingresoDomain.setCategoria(categoriaDomain);
        ingresoDomain.setNombre("prueba");
        ingresoDomain.setDescripcion("prueba");
        ingresoDomain.setMonto(2);
        ingresoDomain.setPeriodicidad(2);
    }

    @Test
    void EditarIngresoExitoso(){
        useCase.execute(ingresoDomain);
        verify(repository, times(1)).save(any());
        assertEquals("prueba", ingresoDomain.getNombre());
    }

    @Test
    void EditarIngresoIntegridadDatos(){
        doThrow(DataIntegrityViolationException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
    }

    @Test
    void EditarIngresoTransaccionException(){
        doThrow(TransactionRequiredException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class, () -> useCase.execute(ingresoDomain));
    }

    @Test
    void EditarIngresoJpaException(){
        doThrow(JpaSystemException.class).when(repository).save(any());
        assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
    }
}
