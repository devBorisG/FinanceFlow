package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.validator.usuario.ContrasenaValidator;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;

@Component
public class ContrasenaValidatorImpl implements ContrasenaValidator {
    @Override
    public void isValid(String contrasena) {
        if (contrasena.length() < 8) {
            throw AplicationCustomException.createUserException("La contraseña debe tener al menos 8 caracteres");
        }
    }
}
