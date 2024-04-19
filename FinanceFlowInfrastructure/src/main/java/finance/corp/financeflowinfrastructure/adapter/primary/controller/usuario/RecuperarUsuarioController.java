package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.token.TokenDTO;
import finance.corp.financeflowapplication.dto.token.builder.TokenBuilder;
import finance.corp.financeflowapplication.dto.token.builder.TokenDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.usuario.EnviarCorreoRecuperacionFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static finance.corp.financeflowutils.helper.UUIDHelper.getNewUUID;

@RestController
@RequestMapping("/finance-flow/v1/usuario/recuperar-cuenta")
public class RecuperarUsuarioController {

    private final EnviarCorreoRecuperacionFacade facade;

    public RecuperarUsuarioController(EnviarCorreoRecuperacionFacade enviarCorreoFacade) {
        this.facade = enviarCorreoFacade;
    }

    @PostMapping()
    public ResponseEntity<Response<String>> execute(@RequestParam String correo){
        TokenDTO tokenDTO = TokenDTOBuilder
                .getTokenDTOBuilder()
                .setToken(getNewUUID())
                .setUsuarioDTO(UsuarioDTOBuilder
                        .getUsuarioDTOBuilder()
                        .setCorreo(correo)
                        .build())
                .build();
        final Response<String> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(tokenDTO);
            response.addSuccessMessage("Se ha enviado el correo de recuperacion, revisa tu bandeja de entrada");
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
