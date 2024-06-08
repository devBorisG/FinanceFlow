package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;
    double monto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
}