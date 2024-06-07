package finance.corp.financeflowapplication.service.usecase.egreso;

import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.EgresoEntity;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import jakarta.persistence.TransactionRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarEgresoUseCaseImplTest {
    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    CategoriaDomain categoria = new CategoriaDomain();
    EgresoDomain egreso = new EgresoDomain();
    @Mock
    EgresoRepository repository;
    @InjectMocks
    ConsultarEgresoUseCaseImpl useCase;

    @BeforeEach
    void setUp(){
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        categoria.setId(id);
        categoria.setNombre("prueba");

        egreso.setId(id);
        egreso.setUsuario(usuario);
        egreso.setCategoria(categoria);
        egreso.setDescripcion("prueba");
    }

    @Test
    void consultarEgresoExitoso(){
        Mockito.when(repository.findByUsuarioId(Mockito.any())).thenReturn(Optional.of(Collections.singletonList(new EgresoEntity())));
        List<EgresoDomain> result = useCase.execute(Optional.of(egreso));
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
    }

    @Test
    void consultarEgresosSinResultado(){
        Mockito.when(repository.findByUsuarioId(Mockito.any())).thenReturn(Optional.empty());
        List<EgresoDomain> result = useCase.execute(Optional.of(egreso));
        assertTrue(result.isEmpty());
    }

    @Test
    void consultarEgresoDataIntegrityException() {
        when(repository.findByUsuarioId(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(egreso)));
    }

    @Test
    void consultarEgresoTransactionException() {
        when(repository.findByUsuarioId(any())).thenThrow(TransactionRequiredException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(egreso)));
    }

    @Test
    void consultarEgresoJpaException() {
        when(repository.findByUsuarioId(any())).thenThrow(JpaSystemException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(egreso)));
    }

}