package finance.corp.financeflowinfrastructure.adapter.primary.dto.egreso.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.categoria.CategoriaDTO;
import finance.corp.financeflowinfrastructure.adapter.primary.dto.egreso.EgresoDTO;
import finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.UsuarioDTO;

import java.util.UUID;

public class EgresoDTOBuilder implements EgresoBuilder{
    private UUID id;
    private String nombre;
    private String descripcion;
    private double monto;
    private int periodicidad;
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;

    private EgresoDTOBuilder() {
        super();
    }

    public static EgresoDTOBuilder getEgresoDTOBuilder() {
        return new EgresoDTOBuilder();
    }

    @Override
    public EgresoDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public EgresoDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public EgresoDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Override
    public EgresoDTOBuilder setMonto(double monto) {
        this.monto = monto;
        return this;
    }

    @Override
    public EgresoDTOBuilder setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
        return this;
    }

    @Override
    public EgresoDTOBuilder setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        return this;
    }

    @Override
    public EgresoDTOBuilder setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
        return this;
    }

    public EgresoDTO build(){
        return EgresoDTO.create(id,nombre,descripcion,monto,periodicidad,usuario,categoria);
    }

    public EgresoDTO buildDefault(){
        return EgresoDTO.create();
    }
}
