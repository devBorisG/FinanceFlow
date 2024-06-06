package finance.corp.financeflowinfrastructure.adapter.primary.controller.egreso;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.service.egreso.CrearEgresoFacade;
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
@RequestMapping("/finance-flow/v1/egreso")
public class CrearEgresoController {
    private final CrearEgresoFacade crearEgresoFacade;

    public CrearEgresoController(CrearEgresoFacade crearEgresoFacade) {
        this.crearEgresoFacade = crearEgresoFacade;
    }
    @PostMapping()
    public ResponseEntity<Response<EgresoDTO>> execute(@RequestBody EgresoDTO dto) {
        dto.setId(getNewUUID());
        final Response<EgresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try {
            crearEgresoFacade.execute(dto);
            response.addSuccessMessage("has registrado el egreso con exito");
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
