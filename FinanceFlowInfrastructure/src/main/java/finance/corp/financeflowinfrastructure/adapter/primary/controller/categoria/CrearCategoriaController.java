package finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static finance.corp.financeflowutils.helper.UUIDHelper.getNewUUID;

@RestController
@RequestMapping("/finance-flow/v1/categoria")
public class CrearCategoriaController {
    private final CrearCategoriaFacade facade;

    public CrearCategoriaController(CrearCategoriaFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public ResponseEntity<Response<CategoriaDTO>> execute(@RequestBody CategoriaDTO dto) {
        dto.setId(getNewUUID());
        final Response<CategoriaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            response.addSuccessMessage("Categoria creada correctamente");
        }catch (final FinanceFlowCustomException e){
            status = HttpStatus.BAD_REQUEST;
            if (e.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error inesperado, por favor intente nuevamente");
            }else{
                response.addErrorMessage(e.getMessage());
            }
        } catch (final Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
