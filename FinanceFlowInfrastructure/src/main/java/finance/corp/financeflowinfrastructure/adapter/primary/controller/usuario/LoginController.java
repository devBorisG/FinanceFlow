package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.authentication.Authenticate;
import finance.corp.financeflowapplication.service.usuario.ConsultarUsuarioFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class LoginController {


    private final Authenticate authenticate;
    private final ConsultarUsuarioFacade consultarUsuarioFacade;

    public LoginController(Authenticate authenticate, ConsultarUsuarioFacade consultarUsuarioFacade) {
        this.authenticate = authenticate;
        this.consultarUsuarioFacade = consultarUsuarioFacade;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response<UsuarioDTO>> login(@RequestBody UsuarioDTO usuarioDTO){
        final Response<UsuarioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            String token = authenticate.authenticate(usuarioDTO);
            response.setToken(token);
            response.setData(consultarUsuarioFacade.execute(Optional.of(usuarioDTO)));
            response.addSuccessMessage("Bienvenido a FinanceFlow");
        } catch(Exception exception){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.addErrorMessage("Acceso no autorizado");
        }
        return new ResponseEntity<>(response,httpStatus);
    }
}
