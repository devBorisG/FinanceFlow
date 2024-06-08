package finance.corp.financeflowapplication.service.usecase.meta;

import finance.corp.financeflowdomain.domain.MetaDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.MetaEntity;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ConsultarMetaUseCaseImplTest {
    static final UUID id = UUID.randomUUID();
    UsuarioDomain usuario = new UsuarioDomain();
    MetaDomain meta = new MetaDomain();
    @Mock
    MetaRepository repository;
    @InjectMocks
    ConsultarMetaUseCaseImpl useCase;

    @BeforeEach
    void setUp(){
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        meta.setId(id);
        meta.setUsuario(usuario);
        meta.setNombre("prueba");
        meta.setDescripcion("prueba");
    }

    @Test
    void consultarMetaExitoso(){
        Mockito.when(repository.findByUsuarioId(Mockito.any())).thenReturn(Optional.of(Collections.singletonList(new MetaEntity())));
        List<MetaDomain> result = useCase.execute(Optional.of(meta));
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
    }

    @Test
    void consultarMetasSinResultado(){
        Mockito.when(repository.findByUsuarioId(Mockito.any())).thenReturn(Optional.empty());
        List<MetaDomain> result = useCase.execute(Optional.of(meta));
        assertTrue(result.isEmpty());
    }

    @Test
    void consultarMetaDataIntegrityException() {
        when(repository.findByUsuarioId(any())).thenThrow(DataIntegrityViolationException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(meta)));
    }

    @Test
    void consultarMetaTransactionException() {
        when(repository.findByUsuarioId(any())).thenThrow(TransactionRequiredException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(meta)));
    }

    @Test
    void consultarMetaJpaException() {
        when(repository.findByUsuarioId(any())).thenThrow(JpaSystemException.class);
        assertThrows(AplicationCustomException.class, () -> useCase.execute(Optional.of(meta)));
    }
}