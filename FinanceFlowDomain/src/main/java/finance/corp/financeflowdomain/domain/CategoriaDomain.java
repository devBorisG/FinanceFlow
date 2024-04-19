package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoriaDomain {
    private UUID id;
    private String nombre;
    private String descripcion;
    private UsuarioDomain usuarioDomain;
}
