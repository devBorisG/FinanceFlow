package finance.corp.financeflowinfrastructure.controller.ingreso;

import finance.corp.financeflowapplication.service.ingreso.EliminarIngresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso.EliminarIngresoController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
class EliminarIngresoControllerIntegrateTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EliminarIngresoFacade facade;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new EliminarIngresoController(facade)).build();
    }

    @Test
    public void eliminarIngresoTestSuccess() throws Exception{
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete("/finance-flow/v1/ingreso").param("id",id.toString()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}