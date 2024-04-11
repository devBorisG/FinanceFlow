package finance.corp.financeflowapplication.service.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.CambioContrasenaFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.usuario.CambioContrasenaUseCase;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CambioContrasenaFacadeImpl implements CambioContrasenaFacade {
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final CambioContrasenaUseCase useCase;

    public CambioContrasenaFacadeImpl(CambioContrasenaUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(UsuarioDTO dto) {

    }
}
