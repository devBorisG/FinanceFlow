package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"Token\"", schema = "public")
public class TokenEntity {
    @Id
    UUID token;
    LocalDateTime fechaCreacion;
    LocalDateTime fechaExpiracion;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
}

