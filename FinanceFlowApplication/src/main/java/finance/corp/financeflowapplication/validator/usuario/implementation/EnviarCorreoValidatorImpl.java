package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.validator.usuario.EnviarCorreoValidator;

public class EnviarCorreoValidatorImpl implements EnviarCorreoValidator {
    @Override
    public void isValid(UsuarioDTO dto) {
        if (dto.getCorreo() == null || dto.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("Correo no puede ser nulo o vacío");
        }
    }
}
