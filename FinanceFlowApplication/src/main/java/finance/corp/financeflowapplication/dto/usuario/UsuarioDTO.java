package finance.corp.financeflowapplication.dto.usuario;

import java.util.UUID;
import static finance.corp.financeflowutils.constant.Constants.EMPTY_STRING;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;
import static finance.corp.financeflowutils.helper.StringHelper.applyTrim;

/**
 * Data Transfer Object (DTO) for the Usuario entity.
 * This class is used to transfer data between different parts of the application.
 * It includes fields for id, nombre, apellido, correo, and contrasena.
 */
public class UsuarioDTO {
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;

    /**
     * Default constructor.
     * Initializes all fields to their default values.
     */
    public UsuarioDTO() {
        setId(DEFAULT_UUID);
        setNombre(EMPTY_STRING);
        setApellido(EMPTY_STRING);
        setCorreo(EMPTY_STRING);
        setContrasena(EMPTY_STRING);
    }

    /**
     * Constructor with parameters.
     * Initializes all fields with the provided values.
     *
     * @param id the UUID of the user
     * @param nombre the name of the user
     * @param apellido the surname of the user
     * @param correo the email of the user
     * @param contrasena the password of the user
     */
    public UsuarioDTO(final UUID id,final String nombre,final String apellido,final String correo,final String contrasena) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setContrasena(contrasena);
    }

    /**
     * Factory method to create a new UsuarioDTO with provided values.
     *
     * @param id the UUID of the user
     * @param nombre the name of the user
     * @param apellido the surname of the user
     * @param correo the email of the user
     * @param contrasena the password of the user
     * @return a new UsuarioDTO with the provided values
     */
    public static UsuarioDTO create(final UUID id,final String nombre,final String apellido,final String correo,final String contrasena) {
        return new UsuarioDTO(id,nombre,apellido,correo,contrasena);
    }

    /**
     * Factory method to create a new UsuarioDTO with default values.
     *
     * @return a new UsuarioDTO with default values
     */
    public static UsuarioDTO create() {
        return new UsuarioDTO();
    }

    /**
     * Getter for id.
     *
     * @return the UUID of the user
     */
    public UUID getId() {
        return id;
    }

    public UsuarioDTO(String id) {
        this.id = UUID.fromString(id);
    }
    /**
     * Setter for id.
     *
     * @param id the UUID of the user
     */
    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    /**
     * Getter for nombre.
     *
     * @return the name of the user
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter for nombre.
     *
     * @param nombre the name of the user
     */
    public void setNombre(String nombre) {
        this.nombre = applyTrim(nombre);
    }

    /**
     * Getter for apellido.
     *
     * @return the surname of the user
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Setter for apellido.
     *
     * @param apellido the surname of the user
     */
    public void setApellido(final String apellido) {
        this.apellido = applyTrim(apellido);
    }

    /**
     * Getter for correo.
     *
     * @return the email of the user
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Setter for correo.
     *
     * @param correo the email of the user
     */
    public void setCorreo(final String correo) {
        this.correo = applyTrim(correo);
    }

    /**
     * Getter for contrasena.
     *
     * @return the password of the user
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Setter for contrasena.
     *
     * @param contrasena the password of the user
     */
    public void setContrasena(final String contrasena) {
        this.contrasena = applyTrim(contrasena);
    }
}