package finance.corp.financeflowapplication.dto.token;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import lombok.Getter;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.helper.DateHelper.getDefaultDate;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TokenDTO {
    private UUID token;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private UsuarioDTO usuarioDTO;

    public TokenDTO() {
        setToken(DEFAULT_UUID);
        setFechaCreacion(LocalDateTime.now());
        setFechaExpiracion(getFechaCreacion().plusMinutes(5));
        setUsuarioDTO(UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }

    public TokenDTO(final UUID token,final UsuarioDTO usuarioDTO) {
        setToken(token);
        setFechaCreacion(LocalDateTime.now());
        setFechaExpiracion(getFechaCreacion().plusMinutes(5));
        setUsuarioDTO(usuarioDTO);
    }

    public static TokenDTO create(final UUID token, final UsuarioDTO usuarioDTO){
        return new TokenDTO(token, usuarioDTO);
    }

    public static TokenDTO create(){
        return new TokenDTO();
    }

    public void setToken(UUID token) {
        this.token = getDefaultUUID(token);
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = getDefaultDate(fechaCreacion);
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = getDefaultDate(fechaExpiracion);
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = getDefaultIfNull(usuarioDTO, UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }
}
