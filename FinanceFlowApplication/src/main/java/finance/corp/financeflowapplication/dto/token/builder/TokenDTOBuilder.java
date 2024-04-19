package finance.corp.financeflowapplication.dto.token.builder;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import java.util.UUID;

public class TokenDTOBuilder implements TokenBuilder{
    private UUID token;
    private UsuarioDTO usuarioDTO;

    private TokenDTOBuilder() {
        super();
    }

    public static TokenDTOBuilder getTokenDTOBuilder() {
        return new TokenDTOBuilder();
    }

    @Override
    public TokenDTOBuilder setToken(UUID token) {
        this.token = token;
        return this;
    }

    @Override
    public TokenDTOBuilder setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
        return this;
    }

    public TokenDTO build() {
        return TokenDTO.create(token, usuarioDTO);
    }

    public TokenDTO buildDefault() {
        return TokenDTO.create();
    }
}
