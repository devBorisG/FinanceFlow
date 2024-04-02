package finance.corp.financeflowdomain.repository.usuario.port;

import finance.corp.financeflowdomain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UsuarioEntity findByCorreo(String correo);
}
