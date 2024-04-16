package finance.corp.financeflowinfrastructure.adapter.secondary.jpa.categoria.implementation;

import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowinfrastructure.adapter.secondary.jpa.categoria.CategoriaRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaCategoriaRepositoryImpl implements CategoriaRepository {
    private final CategoriaRepositoryAdapter categoriaRepositoryAdapter;

    public JpaCategoriaRepositoryImpl(CategoriaRepositoryAdapter categoriaRepositoryAdapter) {
        this.categoriaRepositoryAdapter = categoriaRepositoryAdapter;
    }

    @Override
    public Optional<List<CategoriaEntity>> findByUsuarioId(UUID id) {
        return categoriaRepositoryAdapter.findByUsuario_Id(id);
    }

    @Override
    public void save(CategoriaEntity categoriaEntity) {
        categoriaRepositoryAdapter.save(categoriaEntity);
    }

    @Override
    public boolean existsById(UUID id) {
        return categoriaRepositoryAdapter.existsById(id);
    }

    @Override
    public void delete(CategoriaEntity categoriaEntity) {
        categoriaRepositoryAdapter.delete(categoriaEntity);
    }
}
