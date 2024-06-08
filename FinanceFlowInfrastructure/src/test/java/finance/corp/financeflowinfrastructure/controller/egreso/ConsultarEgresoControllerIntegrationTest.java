package finance.corp.financeflowinfrastructure.controller.egreso;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.service.egreso.ConsultarEgresoFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.egreso.ConsultarEgresoController;
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
public class ConsultarEgresoControllerIntegrationTest {


        @Autowired
        private MockMvc mockMvc;


        @MockBean
        private ConsultarEgresoFacade facade;

        ObjectMapper objectMapper;

        @BeforeEach
        public void setUp() {
            mockMvc = MockMvcBuilders.standaloneSetup(new ConsultarEgresoController(facade)).build();
            objectMapper = new ObjectMapper();

        }
        @Test
        public void ConsultarEgreso_Success() throws Exception {
            UUID id = UUID.randomUUID();
            EgresoDTO egresoDTO = new EgresoDTO();

            List<EgresoDTO> egresoDTOList = Collections.singletonList(egresoDTO);

            when(facade.execute(Optional.of(egresoDTO))).thenReturn(egresoDTOList);

            mockMvc.perform(get("/finance-flow/v1/egreso")
                            .param("id", id.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
}
