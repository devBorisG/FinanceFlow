package finance.corp.financeflowapplication.dto.meta;

import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;

import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.constant.Constants.ZERO_DOUBLE;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_DATE;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;
import static finance.corp.financeflowutils.helper.StringHelper.applyTrim;
import static finance.corp.financeflowutils.helper.NumberHelper.getDefaultDouble;
import static finance.corp.financeflowutils.helper.DateHelper.getDefaultDate;

import java.util.Date;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for the Meta entity.
 * This class is used to transfer data between different parts of the application.
 * It includes fields for id, nombre, descripcion, fechaInicio, fechaFin, monto, and usuario.
 */
public class MetaDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private double monto;
    private UsuarioDTO usuario;

    /**
     * Default constructor.
     * Initializes all fields to their default values.
     */
    public MetaDTO() {
        setId(DEFAULT_UUID);
        setNombre(EMPTY_STRING);
        setDescripcion(EMPTY_STRING);
        setFechaInicio(DEFAULT_DATE);
        setFechaFin(DEFAULT_DATE);
        setMonto(ZERO_DOUBLE);
        setUsuario(UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }

    /**
     * Constructor with parameters.
     * Initializes all fields with the provided values.
     *
     * @param id the UUID of the meta
     * @param nombre the name of the meta
     * @param descripcion the description of the meta
     * @param fechaInicio the start date of the meta
     * @param fechaFin the end date of the meta
     * @param monto the amount of the meta
     * @param usuario the user associated with the meta
     */
    public MetaDTO(final UUID id, final String nombre,final String descripcion,final Date fechaInicio,final Date fechaFin,final double monto,final UsuarioDTO usuario) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setMonto(monto);
        setUsuario(usuario);
    }

    /**
     * Factory method to create a new MetaDTO with provided values.
     *
     * @param id the UUID of the meta
     * @param nombre the name of the meta
     * @param descripcion the description of the meta
     * @param fechaInicio the start date of the meta
     * @param fechaFin the end date of the meta
     * @param monto the amount of the meta
     * @param usuario the user associated with the meta
     * @return a new MetaDTO with the provided values
     */
    public static MetaDTO create(final UUID id, final String nombre,final String descripcion,final Date fechaInicio,final Date fechaFin,final double monto,final UsuarioDTO usuario) {
        return new MetaDTO(id,nombre,descripcion,fechaInicio,fechaFin,monto,usuario);
    }

    /**
     * Factory method to create a new MetaDTO with default values.
     *
     * @return a new MetaDTO with default values
     */
    public static MetaDTO create() {
        return new MetaDTO();
    }

    /**
     * Getter for id.
     *
     * @return the UUID of the meta
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id the UUID of the meta
     */
    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    /**
     * Getter for nombre.
     *
     * @return the name of the meta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter for nombre.
     *
     * @param nombre the name of the meta
     */
    public void setNombre(final String nombre) {
        this.nombre = applyTrim(nombre);
    }

    /**
     * Getter for descripcion.
     *
     * @return the description of the meta
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter for descripcion.
     *
     * @param descripcion the description of the meta
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = applyTrim(descripcion);
    }

    /**
     * Getter for fechaInicio.
     *
     * @return the start date of the meta
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Setter for fechaInicio.
     *
     * @param fechaInicio the start date of the meta
     */
    public void setFechaInicio(final Date fechaInicio) {
        this.fechaInicio = getDefaultDate(fechaInicio);
    }

    /**
     * Getter for fechaFin.
     *
     * @return the end date of the meta
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Setter for fechaFin.
     *
     * @param fechaFin the end date of the meta
     */
    public void setFechaFin(final Date fechaFin) {
        this.fechaFin = getDefaultDate(fechaFin);
    }

    /**
     * Getter for monto.
     *
     * @return the amount of the meta
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Setter for monto.
     *
     * @param monto the amount of the meta
     */
    public void setMonto(final double monto) {
        this.monto = getDefaultDouble(monto);
    }

    /**
     * Getter for usuario.
     *
     * @return the user associated with the meta
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * Setter for usuario.
     *
     * @param usuario the user associated with the meta
     */
    public void setUsuario(final UsuarioDTO usuario) {
        this.usuario = getDefaultIfNull(usuario, UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }
}