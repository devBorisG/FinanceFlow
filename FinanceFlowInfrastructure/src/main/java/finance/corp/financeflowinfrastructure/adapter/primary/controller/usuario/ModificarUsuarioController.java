package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.usuario.implementation.ModificarUsuarioFacadeImpl;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/usuario")

public class ModificarUsuarioController {

    @Autowired
    private final ModificarUsuarioFacadeImpl facade;

    public ModificarUsuarioController(ModificarUsuarioFacadeImpl facade) {
        this.facade = facade;
    }
    @PutMapping
    public ResponseEntity<Response<UsuarioDTO>> execute(@RequestParam UUID id){
        final Response<UsuarioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getUsuarioDTOBuilder().setId(id).build();
        try {
            facade.execute(usuarioDTO);
            response.addSuccessMessage("Usuario modificado correctamente");
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
