package finance.corp.financeflowdomain.repository.usuario;

import finance.corp.financeflowdomain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID>,UsuarioRepositoryCustom {
    Optional<UsuarioEntity> findByCorreo(String correo);
}
