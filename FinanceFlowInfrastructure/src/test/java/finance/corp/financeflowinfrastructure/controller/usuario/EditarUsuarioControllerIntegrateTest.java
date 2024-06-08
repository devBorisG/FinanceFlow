package finance.corp.financeflowinfrastructure.controller.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.usuario.ModificarUsuarioFacade;
import finance.corp.financeflowapplication.service.usuario.implementation.ModificarUsuarioFacadeImpl;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class EditarUsuarioControllerIntegrateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ModificarUsuarioFacadeImpl modificarUsuarioFacade;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID userId;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
        userId = UUID.randomUUID();
    }
    @Test
    void EditarUsuarioTest_Success() throws Exception {
        UUID id = UUID.randomUUID();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        usuarioDTO.setNombre("prueba");
        usuarioDTO.setApellido("prueba");
        usuarioDTO.setContrasena("prueba");
        usuarioDTO.setCorreo("prueba@prueba.com");

        System.out.println(objectMapper.writeValueAsString(usuarioDTO));
        Map<String, Object> respuests = new HashMap<>();
        System.out.println(objectMapper.writeValueAsString(respuests));
        mockMvc.perform(put("/finance-flow/v1/usuario")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk());
    }
}