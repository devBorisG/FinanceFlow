package finance.corp.financeflowdomain.repository.categoria;

import finance.corp.financeflowdomain.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository{
    Optional<List<CategoriaEntity>> findByUsuarioId(UUID id);
    void save(CategoriaEntity categoriaEntity);
    boolean existsById(UUID id);
    void delete(CategoriaEntity categoriaEntity);
}
