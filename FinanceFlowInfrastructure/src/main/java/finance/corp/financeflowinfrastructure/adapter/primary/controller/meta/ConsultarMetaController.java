package finance.corp.financeflowinfrastructure.adapter.primary.controller.meta;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.meta.builder.MetaDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.meta.ConsultarMetaFacade;
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
@RequestMapping("finance-flow/v1/meta")
public class ConsultarMetaController {

    private final ConsultarMetaFacade facade;

    public ConsultarMetaController(ConsultarMetaFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public ResponseEntity<Response<MetaDTO>> execute(@RequestParam UUID id){
        MetaDTO metaDTO = MetaDTOBuilder.getMetaDTOBuilder().setUsuario(UsuarioDTOBuilder.getUsuarioDTOBuilder().setId(id).build()).build();
        final Response<MetaDTO> response = new Response<>();
        HttpStatus status = HttpStatus.OK;
        try{
            List<MetaDTO> dtos = facade.execute(Optional.of(metaDTO));
            response.setData(dtos);
            response.addSuccessMessage("Las metas se han consultado correctamente");
        } catch(final FinanceFlowCustomException exception){
            status = HttpStatus.BAD_REQUEST;
            if(exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error consultando las metas del usuario, intente nuevamente");
            } else{
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error en el sistema, intente nuevamente");
        }
        return new ResponseEntity<>(response,status);
    }
}
