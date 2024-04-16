package finance.corp.financeflowinfrastructure.adapter.primary.controller.categoria;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.categoria.builder.CategoriaDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.categoria.ConsultarCategoriaFacade;
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
@RequestMapping("/finance-flow/v1/categoria")
public class ConsultarCategoriaController {
    private final ConsultarCategoriaFacade facade;

    public ConsultarCategoriaController(ConsultarCategoriaFacade facade) {
        this.facade = facade;
    }

    @GetMapping()
    public ResponseEntity<Response<CategoriaDTO>> execute(@RequestParam UUID id){
        CategoriaDTO categoriaDTO = CategoriaDTOBuilder
                .getCategoriaDTOBuilder()
                .setUsuarioDTO(UsuarioDTOBuilder
                        .getUsuarioDTOBuilder()
                        .setId(id)
                        .build())
                .build();
        final Response<CategoriaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            List<CategoriaDTO> dtos = facade.execute(Optional.of(categoriaDTO));
            response.setData(dtos);
            response.addSuccessMessage("Categorias consultadas correctamente");
        }catch (final FinanceFlowCustomException e){
            status = HttpStatus.BAD_REQUEST;
            if (e.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error inesperado consultando las categorias, por favor intente nuevamente");
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
