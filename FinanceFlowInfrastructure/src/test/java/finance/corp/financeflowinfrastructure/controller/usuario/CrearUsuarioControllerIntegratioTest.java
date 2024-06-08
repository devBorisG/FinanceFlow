package finance.corp.financeflowinfrastructure.controller.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.meta.CrearMetaFacade;
import finance.corp.financeflowapplication.service.usuario.CrearUsuarioFacade;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import finance.corp.financeflowutils.exception.FinanceFlowCustomException;
import finance.corp.financeflowutils.exception.enumeration.LayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class CrearUsuarioControllerIntegratioTest {

    @MockBean
    private CrearUsuarioFacade facade;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void CrearUsuarioTest_Success() throws Exception {
        UUID id = UUID.randomUUID();
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

        System.out.println(objectMapper.writeValueAsString(usuario));
        Map<String, Object> respuests = new HashMap<>();
        System.out.println(objectMapper.writeValueAsString(respuests));
        mockMvc.perform(post("/finance-flow/v1/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(respuests)));
    }
    @Test
    void CrearusuarioTest_BadRequest() throws Exception {
        UUID id = UUID.randomUUID();
        String usuarioJson = "{  \"id\": id, \"nombre\": \"Nombre del usuario\", \"email\": \"email@example.com\" }";
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(id);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setContrasena("prueba");
        usuario.setCorreo("prueba@prueba.com");

       ;

        doThrow(new FinanceFlowCustomException(null, "Technical message", "Nombre de categoria ya existe", LayerException.CONTROLLER))
                .when(facade).execute(usuario);

        mockMvc.perform(post("/finance-flow/v1/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isBadRequest());
    }
}
