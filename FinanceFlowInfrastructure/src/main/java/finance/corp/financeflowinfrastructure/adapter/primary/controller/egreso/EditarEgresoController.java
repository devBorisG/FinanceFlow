package finance.corp.financeflowinfrastructure.adapter.primary.controller.egreso;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.egreso.builder.EgresoDTOBuilder;
import finance.corp.financeflowapplication.service.egreso.EditarEgresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/egreso")
public class EditarEgresoController {

    private final EditarEgresoFacade editarEgresoFacade;

    public EditarEgresoController(EditarEgresoFacade editarEgresoFacade) {
        this.editarEgresoFacade = editarEgresoFacade;

    }
    @PutMapping()
    public ResponseEntity<Response<EgresoDTO>> execute(@RequestParam UUID id){
        final Response<EgresoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        EgresoDTO egresoDTO = EgresoDTOBuilder.getEgresoDTOBuilder().setId(id).build();
        try {
            editarEgresoFacade.execute(egresoDTO);
            response.addSuccessMessage("Egreso modificado correctamente");
        } catch (final AplicationCustomException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error tecnico, intente nuevamente");
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
