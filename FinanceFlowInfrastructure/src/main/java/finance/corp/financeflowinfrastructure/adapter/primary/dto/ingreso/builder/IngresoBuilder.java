package finance.corp.financeflowinfrastructure.adapter.primary.dto.ingreso.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.categoria.CategoriaDTO;
import finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.UsuarioDTO;

import java.util.UUID;

public interface IngresoBuilder {
    IngresoDTOBuilder setId(UUID id);
    IngresoDTOBuilder setNombre(String nombre);
    IngresoDTOBuilder setDescripcion(String descripcion);
    IngresoDTOBuilder setMonto(double monto);
    IngresoDTOBuilder setPeriodicidad(int periodicidad);
    IngresoDTOBuilder setUsuario(UsuarioDTO usuario);
    IngresoDTOBuilder setCategoria(CategoriaDTO categoria);
}
