package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"Token\"", schema = "public")
public class TokenEntity {
    @Id
    String token;
    LocalDateTime fechaCreacion;
    LocalDateTime fechaExpiracion;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    UsuarioEntity usuario;
}

