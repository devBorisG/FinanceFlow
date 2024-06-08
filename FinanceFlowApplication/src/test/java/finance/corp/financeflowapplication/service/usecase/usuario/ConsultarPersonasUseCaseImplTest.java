package finance.corp.financeflowapplication.service.usecase.usuario;

import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarPersonasUseCaseImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ConsultarPersonasUseCaseImpl consultarUsuarioUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        consultarUsuarioUseCase = new ConsultarPersonasUseCaseImpl(usuarioRepository);
    }

    @Test
    public void testExecute_WithDomain() {
        UsuarioDomain usuarioDomain = new UsuarioDomain();
        Optional<UsuarioDomain> optionalUsuarioDomain = Optional.of(usuarioDomain);
        List<UsuarioEntity> mockResult = new ArrayList<>();
        mockResult.add(new UsuarioEntity());
        when(usuarioRepository.findCustom(any())).thenReturn(mockResult);
        List<UsuarioDomain> result = consultarUsuarioUseCase.execute(optionalUsuarioDomain);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findCustom(any());
    }

    @Test
    public void testExecute_NoDomain() {
        List<UsuarioEntity> mockResult = new ArrayList<>();
        mockResult.add(new UsuarioEntity());
        when(usuarioRepository.findAll()).thenReturn(mockResult);
        List<UsuarioDomain> result = consultarUsuarioUseCase.execute(Optional.empty());
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testExecute_Exception() {
        when(usuarioRepository.findAll()).thenThrow(RuntimeException.class);
        assertThrows(DomainCustomException.class, () -> consultarUsuarioUseCase.execute(Optional.empty()));
    }
}
