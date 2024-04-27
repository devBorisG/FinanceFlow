package finance.corp.financeflowinfrastructure.adapter.primary.controller.egreso;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.egreso.builder.EgresoDTOBuilder;
import finance.corp.financeflowapplication.service.egreso.EliminarEgresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/egreso")
public class EliminarEgresoController {
    private final EliminarEgresoFacade eliminarEgresoFacade;

    public EliminarEgresoController(EliminarEgresoFacade eliminarEgresoFacade) {
        this.eliminarEgresoFacade = eliminarEgresoFacade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<EgresoDTO>> execute(@RequestParam UUID id) {
        final Response<EgresoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        EgresoDTO egresoDTO = EgresoDTOBuilder.getEgresoDTOBuilder().setId(id).build();
        try {
            eliminarEgresoFacade.execute(egresoDTO);
            response.addSuccessMessage("Egreso Eliminado correctamente");
        }catch (final AplicationCustomException aplicationCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if (aplicationCustomException.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error eliminando, intente de nuevo");
            } else {
                response.addErrorMessage(aplicationCustomException.getMessage());
            }
        }catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addErrorMessage("Ocurrio un problema con el servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
