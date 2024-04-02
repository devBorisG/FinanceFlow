package finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario;

import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.port.usuario.CrearUsuarioFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance-flow/v1/usuario")
public class CrearUsuarioController {
    private final CrearUsuarioFacade facade;

    public CrearUsuarioController(CrearUsuarioFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> execute(@RequestBody UsuarioDTO dto) {
        facade.execute(dto);
        return ResponseEntity.ok(dto);
    }
}
