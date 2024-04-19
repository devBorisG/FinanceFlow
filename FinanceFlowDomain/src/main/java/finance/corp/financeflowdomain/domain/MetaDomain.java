package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class MetaDomain {
    private UUID id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private double monto;
    private UsuarioDomain usuario;
}
