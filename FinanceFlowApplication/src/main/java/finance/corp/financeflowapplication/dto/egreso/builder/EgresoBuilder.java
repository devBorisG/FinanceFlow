package finance.corp.financeflowapplication.dto.egreso.builder;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import java.util.UUID;

public interface EgresoBuilder {
    EgresoDTOBuilder setId(UUID id);
    EgresoDTOBuilder setNombre(String nombre);
    EgresoDTOBuilder setDescripcion(String descripcion);
    EgresoDTOBuilder setMonto(double monto);
    EgresoDTOBuilder setPeriodicidad(int periodicidad);
    EgresoDTOBuilder setUsuario(UsuarioDTO usuario);
    EgresoDTOBuilder setCategoria(CategoriaDTO categoria);
}
