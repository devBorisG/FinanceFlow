package finance.corp.financeflowapplication.dto.categoria;

import java.util.UUID;

import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import static finance.corp.financeflowutils.helper.StringHelper.applyTrim;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;

/**
 * This class represents a DTO (Data Transfer Object) for the Categoria entity.
 * It is used to transfer data between processes or across network connections.
 */
public class CategoriaDTO {
    private UUID id;
    private String nombre;
    private String descripcion;

    /**
     * Default constructor that initializes the object with default values.
     */
    public CategoriaDTO() {
        setId(DEFAULT_UUID);
        setNombre(EMPTY_STRING);
        setDescripcion(EMPTY_STRING);
    }

    /**
     * Constructor that initializes the object with provided values.
     *
     * @param id The UUID of the Categoria.
     * @param nombre The name of the Categoria.
     * @param descripcion The description of the Categoria.
     */
    public CategoriaDTO(final UUID id,final String nombre,final String descripcion) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    /**
     * Factory method to create a new instance of CategoriaDTO with provided values.
     *
     * @param id The UUID of the Categoria.
     * @param nombre The name of the Categoria.
     * @param descripcion The description of the Categoria.
     * @return A new instance of CategoriaDTO.
     */
    public static CategoriaDTO create(final UUID id,final String nombre,final String descripcion) {
        return new CategoriaDTO(id, nombre, descripcion);
    }

    /**
     * Factory method to create a new instance of CategoriaDTO with default values.
     *
     * @return A new instance of CategoriaDTO.
     */
    public static CategoriaDTO create() {
        return new CategoriaDTO();
    }

    /**
     * Getter for the id.
     *
     * @return The UUID of the Categoria.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the id.
     *
     * @param id The UUID to set.
     */
    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    /**
     * Getter for the nombre.
     *
     * @return The name of the Categoria.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter for the nombre.
     *
     * @param nombre The name to set.
     */
    public void setNombre(final String nombre) {
        this.nombre = applyTrim(nombre);
    }

    /**
     * Getter for the descripcion.
     *
     * @return The description of the Categoria.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter for the descripcion.
     *
     * @param descripcion The description to set.
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = applyTrim(descripcion);
    }
}