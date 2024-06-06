package finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.service.ingreso.EditarIngresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance-flow/v1/ingreso")
public class EditarIngresoController {
   private final  EditarIngresoFacade facade;

    public EditarIngresoController(EditarIngresoFacade facade) {
        this.facade = facade;
    }

    @PutMapping()
    public ResponseEntity<Response<IngresoDTO>> execute(@RequestBody IngresoDTO dto){
        final Response<IngresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try {
            facade.execute(dto);
            response.addSuccessMessage("Ingreso modificado correctamente");
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
