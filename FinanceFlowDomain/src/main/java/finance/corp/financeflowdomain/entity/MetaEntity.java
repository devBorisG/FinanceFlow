package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"Meta\"", schema = "public")
public class MetaEntity {
    @Id
    UUID id;
    String nombre;
    String descripcion;
    Date fechaInicio;
    Date fechaFin;
    double monto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
}
