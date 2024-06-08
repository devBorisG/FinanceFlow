package finance.corp.financeflowinfrastructure.controller.usuario;

import finance.corp.financeflowapplication.service.usuario.EliminarUsuarioFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.usuario.EliminarUsuarioController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
class EliminarUsuarioControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EliminarUsuarioFacade facade;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new EliminarUsuarioController(facade)).build();
    }

    @Test
    public void eliminarUsuarioTestSuccess() throws Exception{
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete("/finance-flow/v1/usuario").param("id",id.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}