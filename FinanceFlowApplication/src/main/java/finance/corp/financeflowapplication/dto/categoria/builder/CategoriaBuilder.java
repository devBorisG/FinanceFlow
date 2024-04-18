package finance.corp.financeflowapplication.dto.categoria.builder;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import java.util.UUID;

public interface CategoriaBuilder {
    CategoriaDTOBuilder setId(UUID id);
    CategoriaDTOBuilder setNombre(String nombre);
    CategoriaDTOBuilder setDescripcion(String descripcion);
    CategoriaDTOBuilder setUsuarioDTO(UsuarioDTO usuarioDTO);
}
