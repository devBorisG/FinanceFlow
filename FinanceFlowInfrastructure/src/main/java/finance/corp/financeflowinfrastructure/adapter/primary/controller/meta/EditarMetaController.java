package finance.corp.financeflowinfrastructure.adapter.primary.controller.meta;


import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.meta.builder.MetaDTOBuilder;
import finance.corp.financeflowapplication.service.meta.EditarMetaFacade;
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
@RequestMapping("/finance-flow/v1/meta")
public class EditarMetaController {
    private final EditarMetaFacade facade;

    public EditarMetaController(EditarMetaFacade facade) {
        this.facade = facade;
    }
    @PutMapping()
    public ResponseEntity<Response<MetaDTO>> execute(@RequestParam UUID id){
        final Response<MetaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        MetaDTO metaDTO = MetaDTOBuilder.getMetaDTOBuilder().setId(id).build();
        try {
            facade.execute(metaDTO);
            response.addSuccessMessage("meta modificado correctamente");
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
