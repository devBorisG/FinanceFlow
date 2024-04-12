package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TokenDomain {
    private UUID token;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private UsuarioDomain usuarioDomain;
}
