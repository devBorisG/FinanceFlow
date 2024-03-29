package finance.corp.financeflowinfrastructure.adapter.primary.dto.meta.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.UsuarioDTO;

import java.util.Date;
import java.util.UUID;

/**
 * Interface for building MetaDTO objects.
 * This interface defines methods for setting the id, nombre, descripcion, monto, fechaInicio, fechaFin, and usuario of a MetaDTO.
 * Each method returns a MetaDTOBuilder, allowing for method chaining.
 */
public interface MetaBuilder {

    /**
     * Sets the id of the MetaDTO.
     *
     * @param id the UUID to set as the id
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setId(UUID id);

    /**
     * Sets the nombre of the MetaDTO.
     *
     * @param nombre the String to set as the nombre
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setNombre(String nombre);

    /**
     * Sets the descripcion of the MetaDTO.
     *
     * @param descripcion the String to set as the descripcion
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setDescripcion(String descripcion);

    /**
     * Sets the monto of the MetaDTO.
     *
     * @param monto the double to set as the monto
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setMonto(double monto);

    /**
     * Sets the fechaInicio of the MetaDTO.
     *
     * @param fechaInicio the Date to set as the fechaInicio
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setFechaInicio(Date fechaInicio);

    /**
     * Sets the fechaFin of the MetaDTO.
     *
     * @param fechaFin the Date to set as the fechaFin
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setFechaFin(Date fechaFin);

    /**
     * Sets the usuario of the MetaDTO.
     *
     * @param usuario the UsuarioDTO to set as the usuario
     * @return the current MetaDTOBuilder
     */
    MetaDTOBuilder setUsuario(UsuarioDTO usuario);
}