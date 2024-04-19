package finance.corp.financeflowapplication.dto.usuario.builder;

import java.util.UUID;

/**
 * Interface for building UsuarioDTO objects.
 * This interface defines methods for setting the id, nombre, apellido, correo, and contrasena of a UsuarioDTO.
 * Each method returns a UsuarioDTOBuilder, allowing for method chaining.
 */
public interface UsuarioBuilder {

    /**
     * Sets the id of the UsuarioDTO.
     *
     * @param id the UUID to set as the id
     * @return the current UsuarioDTOBuilder
     */
    UsuarioDTOBuilder setId(UUID id);

    /**
     * Sets the nombre of the UsuarioDTO.
     *
     * @param nombre the String to set as the nombre
     * @return the current UsuarioDTOBuilder
     */
    UsuarioDTOBuilder setNombre(String nombre);

    /**
     * Sets the apellido of the UsuarioDTO.
     *
     * @param apellido the String to set as the apellido
     * @return the current UsuarioDTOBuilder
     */
    UsuarioDTOBuilder setApellido(String apellido);

    /**
     * Sets the correo of the UsuarioDTO.
     *
     * @param correo the String to set as the correo
     * @return the current UsuarioDTOBuilder
     */
    UsuarioDTOBuilder setCorreo(String correo);

    /**
     * Sets the contrasena of the UsuarioDTO.
     *
     * @param contrasena the String to set as the contrasena
     * @return the current UsuarioDTOBuilder
     */
    UsuarioDTOBuilder setContrasena(String contrasena);
}