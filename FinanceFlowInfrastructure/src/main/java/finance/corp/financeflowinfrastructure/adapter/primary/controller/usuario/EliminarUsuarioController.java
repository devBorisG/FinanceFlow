package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.service.usuario.EliminarUsuarioFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.response.Response;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class EliminarUsuarioController {
    @Autowired
    private final EliminarUsuarioFacade facade;

    public EliminarUsuarioController(EliminarUsuarioFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<UsuarioDTO>> execute(@RequestParam UUID id) {
        final Response<UsuarioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getUsuarioDTOBuilder().setId(id).build();
        try {
            facade.execute(usuarioDTO);
            List<UsuarioDTO> data = new ArrayList<>();
            data.add(usuarioDTO);
            response.addSuccessMessage("Usuario Eliminado correctamente");
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
