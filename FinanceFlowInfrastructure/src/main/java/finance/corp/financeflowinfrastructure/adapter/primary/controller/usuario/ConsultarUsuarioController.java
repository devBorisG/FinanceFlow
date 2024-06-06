package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.ConsultarUsuarioFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class ConsultarUsuarioController {

    private final ConsultarUsuarioFacade facade;

    public ConsultarUsuarioController(ConsultarUsuarioFacade facade) {
        this.facade = facade;
    }

    @GetMapping()
    public ResponseEntity<Response<UsuarioDTO>> execute(@RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> existDto = Optional.ofNullable(usuarioDTO);
        final Response<UsuarioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            List<UsuarioDTO> data = facade.execute(existDto);
            response.addSuccessMessage("Lista de usuarios encontrada");
                response.setData(data);
        }catch(final FinanceFlowCustomException financeFlowCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if(financeFlowCustomException.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error consultando, por favor intente nuevamente");
            }else{
                response.addErrorMessage(financeFlowCustomException.getMessage());
            }
        } catch(final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error con el servidor, intente de nuevo más tarde");
        }
        return new ResponseEntity<>(response,httpStatus);
    }

}
