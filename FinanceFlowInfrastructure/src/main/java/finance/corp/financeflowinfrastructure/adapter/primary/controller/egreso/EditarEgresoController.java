package finance.corp.financeflowinfrastructure.adapter.primary.controller.egreso;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.service.egreso.EditarEgresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finance-flow/v1/egreso")
public class EditarEgresoController {

    private final EditarEgresoFacade editarEgresoFacade;

    public EditarEgresoController(EditarEgresoFacade editarEgresoFacade) {
        this.editarEgresoFacade = editarEgresoFacade;

    }
    @PutMapping()
    public ResponseEntity<Response<EgresoDTO>> execute(@RequestBody EgresoDTO dto){
        final Response<EgresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try {
            editarEgresoFacade.execute(dto);
            response.addSuccessMessage("Egreso modificado correctamente");
        } catch (final AplicationCustomException exception) {
            status = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error tecnico, intente nuevamente");
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
