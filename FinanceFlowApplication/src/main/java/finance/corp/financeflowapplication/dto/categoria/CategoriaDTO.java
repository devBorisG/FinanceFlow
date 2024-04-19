package finance.corp.financeflowapplication.dto.categoria;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import lombok.Getter;

import java.util.UUID;

import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.helper.StringHelper.applyTrim;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;

/**
 * This class represents a DTO (Data Transfer Object) for the Categoria entity.
 * It is used to transfer data between processes or across network connections.
 */
@Getter
public class CategoriaDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
    private UsuarioDTO usuarioDTO;

    /**
     * Default constructor that initializes the object with default values.
     */
    public CategoriaDTO() {
        setId(DEFAULT_UUID);
        setNombre(EMPTY_STRING);
        setDescripcion(EMPTY_STRING);
        setUsuarioDTO(UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }

    /**
     * Constructor that initializes the object with provided values.
     *
     * @param id The UUID of the Categoria.
     * @param nombre The name of the Categoria.
     * @param descripcion The description of the Categoria.
     */
    public CategoriaDTO(final UUID id,final String nombre,final String descripcion, final UsuarioDTO usuarioDTO) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setUsuarioDTO(usuarioDTO);
    }

    /**
     * Factory method to create a new instance of CategoriaDTO with provided values.
     *
     * @param id The UUID of the Categoria.
     * @param nombre The name of the Categoria.
     * @param descripcion The description of the Categoria.
     * @return A new instance of CategoriaDTO.
     */
    public static CategoriaDTO create(final UUID id,final String nombre,final String descripcion, final UsuarioDTO usuarioDTO) {
        return new CategoriaDTO(id, nombre, descripcion, usuarioDTO);
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
     * Setter for the id.
     *
     * @param id The UUID to set.
     */
    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
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
     * Setter for the descripcion.
     *
     * @param descripcion The description to set.
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = applyTrim(descripcion);
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = getDefaultIfNull(usuarioDTO, UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }
}