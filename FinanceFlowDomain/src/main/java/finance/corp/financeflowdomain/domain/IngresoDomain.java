package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IngresoDomain {
    private UUID id;
    private String nombre;
    private String descripcion;
    private double monto;
    private int periodicidad;
    private UsuarioDomain usuario;
    private CategoriaDomain categoria;
}
