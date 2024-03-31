package finance.corp.financeflowdomain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
