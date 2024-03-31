package finance.corp.financeflowapplication.dto.ingreso.builder;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;

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
