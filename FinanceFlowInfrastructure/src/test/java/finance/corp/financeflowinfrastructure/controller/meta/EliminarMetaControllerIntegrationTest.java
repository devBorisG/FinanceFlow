package finance.corp.financeflowinfrastructure.controller.meta;

import finance.corp.financeflowapplication.service.meta.EliminarMetaFacade;
import finance.corp.financeflowinfrastructure.adapter.primary.controller.meta.EliminarMetaController;
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


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FinanceFlowInfrastructureApplication.class)
@AutoConfigureMockMvc
public class EliminarMetaControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;


        @MockBean
        private EliminarMetaFacade facade;

        @BeforeEach
        public void setUp() {

            mockMvc = MockMvcBuilders.standaloneSetup(new EliminarMetaController(facade)).build();
        }

        @Test
        public void eliminarMetaCTest_Success() throws Exception {
            //
            UUID id = UUID.randomUUID();

            mockMvc.perform(delete("/finance-flow/v1/meta")
                            .param("id", id.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

}
