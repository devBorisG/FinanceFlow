package finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.ingreso.CrearIngresoFacade;
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
@RequestMapping("/finance-flow/v1/ingreso")
public class CrearIngresoController {

    private final CrearIngresoFacade facade;

    public CrearIngresoController(CrearIngresoFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<Response<IngresoDTO>> execute(@RequestBody IngresoDTO dto){
        dto.setId(getNewUUID());
        final Response<IngresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            System.out.println("se ejecuta la fachada");
            response.addSuccessMessage("Ingreso creado correctamente");
        } catch(final FinanceFlowCustomException exception){
            status = HttpStatus.BAD_REQUEST;
            if(exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error inesperado, intente nuevamente, bad request");
            } else{
                response.addErrorMessage(exception.getMessage());
            }
        } catch(final Exception exception){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response,status);
    }
}
