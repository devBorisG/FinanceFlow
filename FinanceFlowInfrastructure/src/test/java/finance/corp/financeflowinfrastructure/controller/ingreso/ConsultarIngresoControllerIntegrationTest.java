package finance.corp.financeflowinfrastructure.controller.ingreso;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.service.ingreso.ConsultarIngresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso.ConsultarIngresoController;
import finance.corp.financeflowinfrastructure.init.FinanceFlowInfrastructureApplication;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class ConsultarIngresoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ConsultarIngresoFacade facade;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConsultarIngresoController(facade)).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    public void ConsultarIngreso_Success() throws Exception {
        UUID id = UUID.randomUUID();
        IngresoDTO ingresoDTO = new IngresoDTO();
        List<IngresoDTO> ingresoDTOList = Collections.singletonList(ingresoDTO);
        when(facade.execute(Optional.of(ingresoDTO))).thenReturn(ingresoDTOList);
        mockMvc.perform(get("/finance-flow/v1/ingreso")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
