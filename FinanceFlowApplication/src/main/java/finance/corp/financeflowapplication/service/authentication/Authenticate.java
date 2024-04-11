package finance.corp.financeflowapplication.service.authentication;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

public interface Authenticate {
    String authenticate(UsuarioDTO usuarioDTO);
}
