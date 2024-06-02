package finance.corp.financeflowdomain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"Usuario\"", schema = "public")
public class UsuarioEntity implements UserDetails {
    @Id
    UUID id;
    String nombre;
    String apellido;
    String correo;
    String contrasena;

    @JsonProperty("id")
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("apellido")
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @JsonProperty("correo")
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("usuario"));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return contrasena;
    }

    @JsonProperty("contrasena")
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return correo;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
