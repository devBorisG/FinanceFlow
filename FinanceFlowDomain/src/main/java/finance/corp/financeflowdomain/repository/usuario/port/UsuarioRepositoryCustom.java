package finance.corp.financeflowdomain.repository.usuario.port;

import finance.corp.financeflowdomain.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositoryCustom {
    List<UsuarioEntity> findCustom(UsuarioEntity usuarioEntity);
}
