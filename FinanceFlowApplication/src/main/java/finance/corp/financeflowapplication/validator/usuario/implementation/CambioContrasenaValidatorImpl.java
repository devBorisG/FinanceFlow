package finance.corp.financeflowapplication.validator.usuario.implementation;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.validator.usuario.CambioContrasenaValidator;
import finance.corp.financeflowapplication.validator.usuario.ContrasenaValidator;
import org.springframework.stereotype.Component;


@Component
public class CambioContrasenaValidatorImpl implements CambioContrasenaValidator {
    private final ContrasenaValidator contrasenaValidator;

    public CambioContrasenaValidatorImpl(ContrasenaValidator contrasenaValidator) {
        this.contrasenaValidator = contrasenaValidator;
    }

    @Override
    public void isValid(TokenDTO dto) {
        contrasenaValidator.isValid(dto.getUsuarioDTO().getContrasena());
    }
}
