package finance.corp.financeflowinfrastructure.adapter.primary.controller.meta;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.meta.builder.MetaDTOBuilder;
import finance.corp.financeflowapplication.service.meta.EliminarMetaFacade;
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
@RequestMapping("/finance-flow/v1/meta")
public class EliminarMetaController {
    private final EliminarMetaFacade facade;

    public EliminarMetaController(EliminarMetaFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<MetaDTO>> execute(@RequestParam UUID id) {
        final Response<MetaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        MetaDTO metaDto = MetaDTOBuilder.getMetaDTOBuilder().setId(id).build();
        try {
            facade.execute(metaDto);
            response.addSuccessMessage("Meta Eliminado correctamente");
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
