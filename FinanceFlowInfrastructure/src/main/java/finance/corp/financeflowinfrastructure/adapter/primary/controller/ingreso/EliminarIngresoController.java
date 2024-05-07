package finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.ingreso.builder.IngresoDTOBuilder;
import finance.corp.financeflowapplication.ingreso.EliminarIngresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/ingreso")
public class EliminarIngresoController {

    private final EliminarIngresoFacade facade;

    public EliminarIngresoController(EliminarIngresoFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping
    public ResponseEntity<Response<IngresoDTO>> execute(@RequestParam UUID id){
        IngresoDTO dto = IngresoDTOBuilder.getIngresoDTOBuilder().setId(id).build();
        final Response<IngresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            response.addSuccessMessage("Ingreso eliminado correctamente");
        } catch(final FinanceFlowCustomException exception){
            status = HttpStatus.BAD_REQUEST;
            if(exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error inesperado, intente nuevamente");
            } else{
                response.addErrorMessage(exception.getMessage());
            }
        } catch( final Exception exception){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
