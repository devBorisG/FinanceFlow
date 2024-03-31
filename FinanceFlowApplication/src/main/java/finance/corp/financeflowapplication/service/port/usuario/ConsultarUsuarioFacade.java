package finance.corp.financeflowapplication.service.port.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.port.FindUseCaseFacade;
import finance.corp.financeflowdomain.domain.UsuarioDomain;

public interface ConsultarUsuarioFacade extends FindUseCaseFacade<UsuarioDTO, UsuarioDomain> {
}
