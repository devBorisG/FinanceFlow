package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "Egreso", schema = "public")
public class EgresoEntity {
    @Id
    UUID id;
    String nombre;
    String descripcion;
    double monto;
    int periodicidad;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    CategoriaEntity categoria;
}
