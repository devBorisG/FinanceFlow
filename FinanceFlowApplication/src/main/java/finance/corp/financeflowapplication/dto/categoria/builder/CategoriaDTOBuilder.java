package finance.corp.financeflowapplication.dto.categoria.builder;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;

import java.util.UUID;

public class CategoriaDTOBuilder implements CategoriaBuilder{
    private UUID id;
    private String nombre;
    private String descripcion;

    private CategoriaDTOBuilder() {
        super();
    }

    public static CategoriaDTOBuilder getCategoriaDTOBuilder() {
        return new CategoriaDTOBuilder();
    }

    @Override
    public CategoriaDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public CategoriaDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public CategoriaDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public CategoriaDTO build() {
        return CategoriaDTO.create(id, nombre, descripcion);
    }

    public CategoriaDTO buildDefault() {
        return CategoriaDTO.create();
    }
}
