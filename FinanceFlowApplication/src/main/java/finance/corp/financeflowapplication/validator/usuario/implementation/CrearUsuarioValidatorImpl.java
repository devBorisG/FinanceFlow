package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.validator.usuario.CorreoValidator;
import finance.corp.financeflowapplication.validator.usuario.CrearUsuarioValidator;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;
import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;
import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CrearUsuarioValidatorImpl implements CrearUsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final CorreoValidator correoValidator;

    public CrearUsuarioValidatorImpl(UsuarioRepository usuarioRepository, CorreoValidator correoValidator) {
        this.usuarioRepository = usuarioRepository;
        this.correoValidator = correoValidator;
    }

    @Override
    public void isValid(UsuarioDTO dto) {
        verifyUserIntegrity(dto);
    }

    private void verifyUserIntegrity(UsuarioDTO dto) {
        correoValidator.isValid(dto.getCorreo());
        if (verifyMandatoryUserFields(dto)) {
            throw AplicationCustomException.createUserException("Todos los campos son obligatorios");
        }
        if (usuarioRepository.findById(dto.getId()).isPresent()) {
            throw AplicationCustomException.createUserException("Ocurrio un error, por favor intente nuevamente");
        }
        if (isOnlyLettersAndSpaces(dto.getNombre()+dto.getApellido())){
            throw AplicationCustomException.createUserException("Nombres y apellidos solo pueden contener letras y espacios");
        }
    }

    private boolean isOnlyLettersAndSpaces(String name) {
          String nameRegex = "^[a-zA-Z\\s]*$";
          Pattern pattern = Pattern.compile(nameRegex);
          Matcher matcher = pattern.matcher(name);
          return !matcher.matches();
    }

    private boolean verifyMandatoryUserFields(UsuarioDTO dto) {
        return isDefaultUUID(dto.getId()) || isEmpty(dto.getNombre()) || isEmpty(dto.getApellido()) ||
                isEmpty(dto.getCorreo()) || isEmpty(dto.getContrasena());
    }

}
