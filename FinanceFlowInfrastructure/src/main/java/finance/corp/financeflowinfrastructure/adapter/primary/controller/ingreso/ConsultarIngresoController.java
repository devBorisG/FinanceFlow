package finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso;

import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.ingreso.builder.IngresoDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.ingreso.ConsultarIngresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("finance-flow/v1/ingreso")
public class ConsultarIngresoController {

    private final ConsultarIngresoFacade facade;

    public ConsultarIngresoController(ConsultarIngresoFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public ResponseEntity<Response<IngresoDTO>> execute(@RequestParam UUID id){
        IngresoDTO ingresoDTO = IngresoDTOBuilder.getIngresoDTOBuilder().setUsuario(UsuarioDTOBuilder.getUsuarioDTOBuilder().setId(id).build()).build();
        final Response<IngresoDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            List<IngresoDTO> dtos = facade.execute(Optional.of(ingresoDTO));
            response.setData(dtos);
            response.addSuccessMessage("Los ingresos se han consultado correctamente");
        } catch(final FinanceFlowCustomException exception){
            status = HttpStatus.BAD_REQUEST;
            if(exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error consultando los ingresos del usuario, intente nuevamente");
            } else{
                response.addErrorMessage(exception.getMessage());
            }
        } catch(final Exception exception){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error con el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response,status);
    }
}
