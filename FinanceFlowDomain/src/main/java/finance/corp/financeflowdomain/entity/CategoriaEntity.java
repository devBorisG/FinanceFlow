package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "\"Categoria\"", schema = "public")
public class CategoriaEntity {
    @Id
    UUID id;
    String nombre;
    String descripcion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
}
