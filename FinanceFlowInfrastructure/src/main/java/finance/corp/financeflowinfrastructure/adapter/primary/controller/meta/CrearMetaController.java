package finance.corp.financeflowinfrastructure.adapter.primary.controller.meta;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.meta.CrearMetaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static finance.corp.financeflowutils.helper.UUIDHelper.getNewUUID;

@RestController
@RequestMapping("/finance-flow/v1/meta")
public class CrearMetaController {
    private final CrearMetaFacade facade;

    public CrearMetaController(CrearMetaFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<Response<MetaDTO>> execute(@RequestBody MetaDTO dto){
        dto.setId(getNewUUID());
        final Response<MetaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            response.setData(List.of(dto));
            response.addSuccessMessage("Meta creada correctamente");
        } catch(final FinanceFlowCustomException exception){
            status = HttpStatus.BAD_REQUEST;
            if(exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error inesperado, intente nuevamente");
            } else{
                response.addErrorMessage(exception.getMessage());
            }
        } catch(final Exception exception){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, intente nuevamente por favor");
        }
        return new ResponseEntity<>(response,status);
    }
}
