package finance.corp.financeflowapplication.dto.token.builder;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import java.util.UUID;

public interface TokenBuilder {
    TokenDTOBuilder setToken(UUID token);
    TokenDTOBuilder setUsuarioDTO(UsuarioDTO usuarioDTO);
}
