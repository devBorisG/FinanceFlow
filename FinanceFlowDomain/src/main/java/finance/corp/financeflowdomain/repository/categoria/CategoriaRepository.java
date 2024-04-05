package finance.corp.financeflowdomain.repository.categoria;

import finance.corp.financeflowdomain.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID>{
    CategoriaEntity findByNombre(String nombre);
}
