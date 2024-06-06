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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
@ExtendWith(MockitoExtension.class)
public class EliminarIngresoUseCaseImplTest {

    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
        CategoriaDomain categoriaDomain = new CategoriaDomain();
        IngresoDomain ingresoDomain = new IngresoDomain();

        @Mock
        private IngresoRepository ingresoRepository;

        @InjectMocks
        private EliminarIngresoUseCaseImpl useCase;

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

            ingresoDomain.setCategoria(categoriaDomain);
            ingresoDomain.setUsuario(usuario);
            ingresoDomain.setId(id);
            ingresoDomain.setNombre("prueba");
            ingresoDomain.setDescripcion("prueba");
            ingresoDomain.setMonto(12323);
            ingresoDomain.setPeriodicidad(1);
        }

        @Test
        void EliminarIngreso_exitoso() {
            useCase.execute(ingresoDomain);
            verify(ingresoRepository, times(1)).delete(any());
            assertEquals(id, ingresoDomain.getId());
        }

        @Test
        void EliminarIngresoIntegridadDatos(){
            doThrow(DataIntegrityViolationException.class).when(ingresoRepository).delete(any());
            assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
        }

        @Test
        void EliminarIngresoTransaccionException(){
            doThrow(TransactionRequiredException.class).when(ingresoRepository).delete(any());
            assertThrows(AplicationCustomException.class, () -> useCase.execute(ingresoDomain));
        }

        @Test
        void EliminarIngresoJpaException(){
            doThrow(JpaSystemException.class).when(ingresoRepository).delete(any());
            assertThrows(AplicationCustomException.class,()->useCase.execute(ingresoDomain));
        }

    }
