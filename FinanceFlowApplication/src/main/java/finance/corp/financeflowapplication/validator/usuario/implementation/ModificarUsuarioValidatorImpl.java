package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.validator.usuario.CorreoValidator;
import finance.corp.financeflowapplication.validator.usuario.ModificarUsuarioValidator;
import finance.corp.financeflowdomain.repository.usuario.UsuarioRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;
import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;


@Service
public class ModificarUsuarioValidatorImpl implements ModificarUsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final CorreoValidator correoValidator;

    public ModificarUsuarioValidatorImpl(UsuarioRepository usuarioRepository, CorreoValidator correoValidator) {
        this.usuarioRepository = usuarioRepository;
        this.correoValidator = correoValidator;
    }

    @Override
    public void isValid(UsuarioDTO dto) {
        validarUsuario(dto);

    }

    private void validarUsuario(UsuarioDTO dto) {
        correoValidator.isValid(dto.getCorreo());

        if (usuarioRepository.findById(dto.getId()).isEmpty()) {
            throw AplicationCustomException.createUserException("El usuario que desea actualizar no existe");
        }
        if (isOnlyLettersAndSpaces(dto.getNombre() + dto.getApellido())) {
            throw AplicationCustomException.createUserException("Nombres y apellidos solo pueden contener letras y espacios");
        }
        if (verifyMandatoryUserFields(dto)) {
            throw AplicationCustomException.createUserException("Todos los campos son obligatorios");
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
