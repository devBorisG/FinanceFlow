package finance.corp.financeflowapplication.dto.meta.builder;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Builder class for MetaDTO.
 * This class implements the MetaBuilder interface and provides methods to set the id, nombre, descripcion, monto, fechaInicio, fechaFin, and usuario of a MetaDTO.
 * It also provides methods to build a MetaDTO with the set values or with default values.
 */
public class MetaDTOBuilder implements MetaBuilder{
    private UUID id;
    private String nombre;
    private String descripcion;
    private double monto;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private UsuarioDTO usuario;

    /**
     * Private constructor to prevent instantiation without using the factory method.
     */
    private MetaDTOBuilder() {
        super();
    }

    /**
     * Factory method to get a new instance of MetaDTOBuilder.
     *
     * @return a new instance of MetaDTOBuilder
     */
    public static MetaDTOBuilder getMetaDTOBuilder() {
        return new MetaDTOBuilder();
    }

    /**
     * Sets the id of the MetaDTO.
     *
     * @param id the UUID to set as the id
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the nombre of the MetaDTO.
     *
     * @param nombre the String to set as the nombre
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * Sets the descripcion of the MetaDTO.
     *
     * @param descripcion the String to set as the descripcion
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    /**
     * Sets the monto of the MetaDTO.
     *
     * @param monto the double to set as the monto
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setMonto(double monto) {
        this.monto = monto;
        return this;
    }

    /**
     * Sets the fechaInicio of the MetaDTO.
     *
     * @param fechaInicio the Date to set as the fechaInicio
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    /**
     * Sets the fechaFin of the MetaDTO.
     *
     * @param fechaFin the Date to set as the fechaFin
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    /**
     * Sets the usuario of the MetaDTO.
     *
     * @param usuario the UsuarioDTO to set as the usuario
     * @return the current MetaDTOBuilder
     */
    @Override
    public MetaDTOBuilder setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        return this;
    }

    /**
     * Builds a MetaDTO with the set values.
     *
     * @return a MetaDTO with the set values
     */
    public MetaDTO build() {
        return MetaDTO.create(id, nombre, descripcion, fechaInicio, fechaFin, monto ,usuario);
    }

    /**
     * Builds a MetaDTO with default values.
     *
     * @return a MetaDTO with default values
     */
    public MetaDTO buildDefault() {
        return MetaDTO.create();
    }
}