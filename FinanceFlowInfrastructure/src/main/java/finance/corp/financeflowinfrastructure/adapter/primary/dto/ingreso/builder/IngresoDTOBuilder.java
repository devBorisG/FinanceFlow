package finance.corp.financeflowinfrastructure.adapter.primary.dto.ingreso.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.categoria.CategoriaDTO;
import finance.corp.financeflowinfrastructure.adapter.primary.dto.ingreso.IngresoDTO;
import finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.UsuarioDTO;

import java.util.UUID;

public class IngresoDTOBuilder implements IngresoBuilder{
    private UUID id;
    private String nombre;
    private String descripcion;
    private double monto;
    private int periodicidad;
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;

    private IngresoDTOBuilder() {
        super();
    }

    public static IngresoDTOBuilder getIngresoDTOBuilder() {
        return new IngresoDTOBuilder();
    }

    @Override
    public IngresoDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public IngresoDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public IngresoDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Override
    public IngresoDTOBuilder setMonto(double monto) {
        this.monto = monto;
        return this;
    }

    @Override
    public IngresoDTOBuilder setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
        return this;
    }

    @Override
    public IngresoDTOBuilder setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        return this;
    }

    @Override
    public IngresoDTOBuilder setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
        return this;
    }

    public IngresoDTO build(){
        return IngresoDTO.create(id,nombre,descripcion,monto,periodicidad,usuario,categoria);
    }

    public IngresoDTO buildDefault(){
        return IngresoDTO.create();
    }
}
