package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.token.builder.TokenDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.CambioContrasenaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/usuario/recuperar-cuenta")
public class CambioContrasenaController {
    private final CambioContrasenaFacade facade;

    public CambioContrasenaController(CambioContrasenaFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/cambio-contrasena")
    public ResponseEntity<Response<String>> execute(@RequestParam UUID token, @RequestBody UsuarioDTO usuarioDTO){
        TokenDTO tokenDTO = TokenDTOBuilder
                .getTokenDTOBuilder()
                .setToken(token)
                .build();
        final Response<String> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
//            facade.execute(tokenDTO, usuarioDTO);
            response.addSuccessMessage("Token valido");
        }catch (final FinanceFlowCustomException e){
            status = HttpStatus.BAD_REQUEST;
            if (e.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error inesperado, por favor intente nuevamente");
            }else {
                response.addErrorMessage(e.getMessage());
            }
        }catch (final Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
