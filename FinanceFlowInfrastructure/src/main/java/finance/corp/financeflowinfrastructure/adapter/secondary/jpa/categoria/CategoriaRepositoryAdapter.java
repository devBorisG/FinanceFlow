package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.categoria;

import finance.corp.financeflowdomain.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepositoryAdapter extends JpaRepository<CategoriaEntity, UUID> {
    Optional<List<CategoriaEntity>> findByUsuario_Id(UUID id);
}
