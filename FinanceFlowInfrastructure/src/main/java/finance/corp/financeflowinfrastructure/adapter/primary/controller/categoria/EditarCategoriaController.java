package finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.EditarCategoriaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance-flow/v1/categoria")
public class EditarCategoriaController {
    private final EditarCategoriaFacade facade;

    public EditarCategoriaController(EditarCategoriaFacade facade) {
        this.facade = facade;
    }

    @PutMapping()
    public ResponseEntity<Response<CategoriaDTO>> execute(@RequestBody CategoriaDTO dto){
        final Response<CategoriaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            facade.execute(dto);
            response.addSuccessMessage("Categoria editada correctamente");
        }catch (FinanceFlowCustomException e){
            status = HttpStatus.BAD_REQUEST;
            if (e.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error inesperado, por favor intente nuevamente");
            }else {
                response.addErrorMessage(e.getMessage());
            }
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, por favor intente nuevamente");
        }
        return new ResponseEntity<>(response, status);
    }
}
