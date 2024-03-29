package finance.corp.financeflowinfrastructure.adapter.primary.dto.categoria.builder;

import java.util.UUID;

public interface CategoriaBuilder {
    CategoriaDTOBuilder setId(UUID id);
    CategoriaDTOBuilder setNombre(String nombre);
    CategoriaDTOBuilder setDescripcion(String descripcion);
}
