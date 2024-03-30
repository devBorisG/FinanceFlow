package finance.corp.financeflowdomain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "Usuario", schema = "public")
public class UsuarioEntity {
    @Id
    UUID id;
    String nombre;
    String apellido;
    String correo;
    String contrasena;
}
