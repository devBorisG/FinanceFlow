package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.CrearUsuarioFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static finance.corp.financeflowutils.helper.UUIDHelper.getNewUUID;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class CrearUsuarioController {
    private final CrearUsuarioFacade facade;

    public CrearUsuarioController(CrearUsuarioFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public ResponseEntity<Response<UsuarioDTO>> execute(@RequestBody UsuarioDTO dto) {
        dto.setId(getNewUUID());
        final Response<UsuarioDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            response.addSuccessMessage("Te has registrado correctamente");
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
