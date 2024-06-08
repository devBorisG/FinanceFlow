package finance.corp.financeflowinfrastructure.controller.meta;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.service.ingreso.ConsultarIngresoFacade;
import finance.corp.financeflowapplication.service.meta.ConsultarMetaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.ingreso.ConsultarIngresoController;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.meta.ConsultarMetaController;
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
public class ConsultarMetaControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultarMetaFacade facade;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConsultarMetaController(facade)).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    public void ConsultarMeta_Success() throws Exception {
        UUID id = UUID.randomUUID();
        MetaDTO metaDTO = new MetaDTO();

        List<MetaDTO> metaDTOList = Collections.singletonList(metaDTO);

        when(facade.execute(Optional.of(metaDTO))).thenReturn(metaDTOList);

        mockMvc.perform(get("/finance-flow/v1/meta")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
