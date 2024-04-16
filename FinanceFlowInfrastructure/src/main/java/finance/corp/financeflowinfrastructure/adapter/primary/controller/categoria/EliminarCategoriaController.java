package finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.categoria.builder.CategoriaDTOBuilder;
import finance.corp.financeflowapplication.service.categoria.EliminarCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/categoria")
public class EliminarCategoriaController {
    private final EliminarCategoriaFacade facade;

    public EliminarCategoriaController(EliminarCategoriaFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<CategoriaDTO>> execute(@RequestParam UUID id) {
        CategoriaDTO dto = CategoriaDTOBuilder
                .getCategoriaDTOBuilder()
                .setId(id)
                .build();
        final Response<CategoriaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try {
            facade.execute(dto);
            response.addSuccessMessage("Categoria eliminada correctamente");
        }catch (final FinanceFlowCustomException e){
            status = HttpStatus.BAD_REQUEST;
            if (e.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error inesperado, por favor intente nuevamente");
            }else{
                response.addErrorMessage(e.getMessage());
            }
        } catch (final Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
