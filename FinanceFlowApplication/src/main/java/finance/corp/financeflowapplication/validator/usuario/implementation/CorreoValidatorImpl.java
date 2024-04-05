package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.validator.usuario.CorreoValidator;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.mapper.MapperDomainToEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CorreoValidatorImpl implements CorreoValidator {

    private final UsuarioRepository usuarioRepository;
    MapperDomainToEntity<UsuarioDomain, UsuarioEntity> mapperDomainToEntity = new MapperDomainToEntity<>();

    public CorreoValidatorImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void isValid(String correo) {
        if (isPatternValid(correo)){
            throw AplicationCustomException.createUserException("El correo ingresado no es valido");
        }
        if (usuarioRepository.findByCorreo(correo) != null){
            throw AplicationCustomException.createUserException("El correo ya esta registrado en la aplicacion");
        }
    }

    private boolean isPatternValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }
}
