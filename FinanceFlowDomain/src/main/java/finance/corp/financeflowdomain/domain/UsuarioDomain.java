package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioDomain {
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
}
