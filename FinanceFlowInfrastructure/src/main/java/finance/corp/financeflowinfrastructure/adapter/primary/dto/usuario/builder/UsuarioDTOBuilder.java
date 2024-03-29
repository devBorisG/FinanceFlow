package finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.builder;

import finance.corp.financeflowinfrastructure.adapter.primary.dto.usuario.UsuarioDTO;

import java.util.UUID;

/**
 * Builder class for UsuarioDTO.
 * This class implements the UsuarioBuilder interface and provides methods to set the id, nombre, apellido, correo, and contrasena of a UsuarioDTO.
 * It also provides methods to build a UsuarioDTO with the set values or with default values.
 */
public class UsuarioDTOBuilder implements UsuarioBuilder{
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;

    /**
     * Private constructor to prevent instantiation without using the factory method.
     */
    private UsuarioDTOBuilder() {
        super();
    }

    /**
     * Factory method to get a new instance of UsuarioDTOBuilder.
     *
     * @return a new instance of UsuarioDTOBuilder
     */
    public static UsuarioDTOBuilder getUsuarioDTOBuilder() {
        return new UsuarioDTOBuilder();
    }

    /**
     * Sets the id of the UsuarioDTO.
     *
     * @param id the UUID to set as the id
     * @return the current UsuarioDTOBuilder
     */
    @Override
    public UsuarioDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the nombre of the UsuarioDTO.
     *
     * @param nombre the String to set as the nombre
     * @return the current UsuarioDTOBuilder
     */
    @Override
    public UsuarioDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * Sets the apellido of the UsuarioDTO.
     *
     * @param apellido the String to set as the apellido
     * @return the current UsuarioDTOBuilder
     */
    @Override
    public UsuarioDTOBuilder setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    /**
     * Sets the correo of the UsuarioDTO.
     *
     * @param correo the String to set as the correo
     * @return the current UsuarioDTOBuilder
     */
    @Override
    public UsuarioDTOBuilder setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    /**
     * Sets the contrasena of the UsuarioDTO.
     *
     * @param contrasena the String to set as the contrasena
     * @return the current UsuarioDTOBuilder
     */
    @Override
    public UsuarioDTOBuilder setContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    /**
     * Builds a UsuarioDTO with the set values.
     *
     * @return a UsuarioDTO with the set values
     */
    public UsuarioDTO build() {
        return UsuarioDTO.create(id, nombre, apellido, correo, contrasena);
    }

    /**
     * Builds a UsuarioDTO with default values.
     *
     * @return a UsuarioDTO with default values
     */
    public UsuarioDTO buildDefault() {
        return UsuarioDTO.create();
    }
}