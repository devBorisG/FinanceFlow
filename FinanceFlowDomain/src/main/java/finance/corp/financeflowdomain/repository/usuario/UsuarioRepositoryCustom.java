package finance.corp.financeflowdomain.repository.usuario;

import finance.corp.financeflowdomain.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepositoryCustom {
    List<UsuarioEntity> findCustom(UsuarioEntity usuarioEntity);
}
